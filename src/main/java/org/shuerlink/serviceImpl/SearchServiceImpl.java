package org.shuerlink.serviceImpl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.shuerlink.crawler.CallableSpider;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCallablePageProcessor;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BingImageCallablePageProcessor;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.GoogleImageCallablePageProcessor;
import org.shuerlink.crawlerImpl.VedioCrawlerImpl.BaiduVedioCallablePageProcessor;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCallablePageProcessor;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BingWebpageCallablePageProcessor;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.GoogleWebpageCallablePageProcessor;
import org.shuerlink.model.*;
import org.shuerlink.service.SearchService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

/**
 * @author VATALU
 * @version 0.1
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private BaiduImageCallablePageProcessor baiduImageCallablePageProcessor;
    @Resource
    private BaiduWebpageCallablePageProcessor baiduWebpageCallablePageProcessor;
    @Resource
    private BaiduVedioCallablePageProcessor baiduVedioCallablePageProcessor;
    @Resource
    private BingImageCallablePageProcessor bingImageCallablePageProcessor;
    @Resource
    private BingWebpageCallablePageProcessor bingWebpageCallablePageProcessor;
    @Resource
    private GoogleImageCallablePageProcessor googleImageCallablePageProcessor;
    @Resource
    private GoogleWebpageCallablePageProcessor googleWebpageCallablePageProcessor;

    /*
    * 搜索网页
    * */
    @Override
    public List<WebPageResult> getWebpage(String keyword, int start, int num) {
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                baiduWebpageCallablePageProcessor,
//              googleWebpageCallablePageProcessor,
                bingWebpageCallablePageProcessor
        ).setThreadPoolTask(taskExecutor);

        List<WebPageResult> webPageResults = callableSpider.call();

        /*
        * 排序
        * */
        webPageResults.sort((t1, t2) -> (t1.compareTo(t2)));
        return webPageResults;
    }

    /*
    * 搜索照片
    * */
    @Override
    public List<ImageResult> getImage(String keyword, int start, int num) {

        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                baiduImageCallablePageProcessor,
//                googleImageCallablePageProcessor,
                bingImageCallablePageProcessor).setThreadPoolTask(taskExecutor);
        List<ImageResult> imageResults = callableSpider.call();
        /*
        *url排序
         */
        imageResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return imageResults;
    }

    /*
    * 搜索视频
    * */
    @Override
    public List<VedioResult> getVedio(String keyword, int start, int num) {
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                baiduVedioCallablePageProcessor).setThreadPoolTask(taskExecutor);
        List<VedioResult> vedioResults = callableSpider.call();

        vedioResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return vedioResults;
    }

}
