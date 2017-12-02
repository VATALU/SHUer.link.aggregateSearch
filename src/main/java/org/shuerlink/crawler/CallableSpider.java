package org.shuerlink.crawler;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableSpider implements Task {
    protected Downloader downloader;
    protected List<CallablePageProcessor> callablePageProcessors = new LinkedList<>();
    protected Site site;
    protected String uuid;
    protected Scheduler scheduler = new QueueScheduler();
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected ThreadPoolTaskExecutor threadPoolTaskExecutor;
    protected AtomicInteger stat = new AtomicInteger(STAT_INIT);
    protected final static int STAT_INIT = 0;
    protected final static int STAT_RUNNING = 1;
    protected final static int STAT_STOPPED = 2;
    protected int threadNum = 1;
    private Date startTime;
    private String keyword;
    private int start;
    private int num;

    public CallableSpider(String keyword, int start, int num, CallablePageProcessor... callablePageProcessors) {
        for (CallablePageProcessor callablePageProcessor : callablePageProcessors) {
            this.callablePageProcessors.add(callablePageProcessor);
            this.site = callablePageProcessor.getSite();
        }
        this.threadNum = callablePageProcessors.length;
        this.keyword = keyword;
        this.start = start;
        this.num = num;
    }

    public static CallableSpider newInstance(String keyword, int start, int num, CallablePageProcessor... callablePageProcessors) {
        return new CallableSpider(keyword, start, num, callablePageProcessors);
    }

    public CallableSpider setScheduler(Scheduler scheduler) {
        checkIfRunning();
        Scheduler oldScheduler = this.scheduler;
        this.scheduler = scheduler;
        if (oldScheduler != null) {
            Request request;
            while ((request = oldScheduler.poll(this)) != null) {
                this.scheduler.push(request, this);
            }
        }
        return this;
    }

    public CallableSpider setDownloader(Downloader downloader) {
        checkIfRunning();
        this.downloader = downloader;
        return this;
    }

    public CallableSpider setThreadPoolTask(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        checkIfRunning();
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        return this;
    }

    private void initComponent() {
        if (downloader == null) {
            this.downloader = new HttpClientDownloader();
        }
        downloader.setThread(threadNum);
        if (threadPoolTaskExecutor == null) {
            threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.setCorePoolSize(6);
            threadPoolTaskExecutor.setMaxPoolSize(50);
            threadPoolTaskExecutor.setQueueCapacity(30);
            threadPoolTaskExecutor.setKeepAliveSeconds(60000);
        }
        startTime = new Date();
    }

    public <T> List<T> call() {
        checkRunningStat();
        initComponent();
        logger.info("Spider {} started!", getUUID());
        List<T> results = new LinkedList<>();
        try {
            ArrayList<Future<List<T>>> futureArrayList = new ArrayList<>();

            Iterator iterator = callablePageProcessors.iterator();
            while (iterator.hasNext()) {
                CallablePageProcessor callablePageProcessor = (CallablePageProcessor) iterator.next();
                Request request = new Request(callablePageProcessor.getUrl(keyword, start, num));
                futureArrayList.add(threadPoolTaskExecutor.submit(() -> processRequest(request, callablePageProcessor)));
            }
            for (Future<List<T>> future : futureArrayList) {
                results.addAll(future.get());
            }

            results = new LinkedList<>(new LinkedHashSet<>(results));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }

    private <T> List<T> processRequest(Request request, CallablePageProcessor callablePageProcessor) {
        Page page = downloader.download(request, this);
        if (page.isDownloadSuccess()) {
            return onDownloadSuccess(request, page, callablePageProcessor);
        } else {
            onDownloaderFail(request);
            return null;
        }
    }

    private <T> List<T> onDownloadSuccess(Request request, Page page, CallablePageProcessor callablePageProcessor) {
        if (site.getAcceptStatCode().contains(page.getStatusCode())) {
            return callablePageProcessor.getResults(page);
//            if (!page.getResultItems().isSkip()) {
//                for (Pipeline pipeline : pipelines) {
//                    pipeline.process(page.getResultItems(), this);
//                }
//            }
        } else {
            logger.info("page status code error, page {} , code: {}", request.getUrl(), page.getStatusCode());
        }
        return null;
    }

    private void onDownloaderFail(Request request) {
        if (site.getCycleRetryTimes() != 0) {
            doCycleRetry(request);
        }
    }

    private void doCycleRetry(Request request) {
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes < site.getCycleRetryTimes()) {
                addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
            }
        }
        sleep(site.getRetrySleepTime());
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted when sleep", e);
        }
    }

    private void checkRunningStat() {
        while (true) {
            int statNow = stat.get();
            if (statNow == STAT_RUNNING) {
                throw new IllegalStateException("Spider is already running!");
            }
            if (stat.compareAndSet(statNow, STAT_RUNNING)) {
                break;
            }
        }
    }

    @Override
    public String getUUID() {
        if (uuid != null) {
            return uuid;
        }
        if (site != null) {
            return site.getDomain();
        }
        uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public CallableSpider setUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public Site getSite() {
        return site;
    }

    protected void checkIfRunning() {
        if (stat.get() == STAT_RUNNING) {
            throw new IllegalStateException("Spider is already running!");
        }
    }

    public void stop() {
        if (stat.compareAndSet(STAT_RUNNING, STAT_STOPPED)) {
            logger.info("Spider " + getUUID() + " stop success!");
        } else {
            logger.info("Spider " + getUUID() + " stop fail!");
        }
    }

    public CallableSpider thread(int threadNum) {
        checkIfRunning();
        this.threadNum = threadNum;
        if (threadNum <= 0) {
            throw new IllegalArgumentException("threadNum should be more than one!");
        }
        return this;
    }

    public CallableSpider addUrl(String... urls) {
        for (String url : urls) {
            addRequest(new Request(url));
        }
        return this;
    }

    public CallableSpider addRequest(Request... requests) {
        for (Request request : requests) {
            addRequest(request);
        }
        return this;
    }

    private void addRequest(Request request) {
        if (site.getDomain() == null && request != null && request.getUrl() != null) {
            site.setDomain(UrlUtils.getDomain(request.getUrl()));
        }
        scheduler.push(request, this);
    }

}
