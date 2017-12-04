package test.org.shuerlink.crawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shuerlink.crawler.CallableSpider;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BingImageCallablePageProcessor;
import org.shuerlink.crawlerImpl.VedioCrawlerImpl.BingVedioCallablePageProcessor;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BingWebpageCallablePageProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})//加载spring配置文件
public class BingCallablePageProcessorTest {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private BingWebpageCallablePageProcessor bingWebpageCallablePageProcessor;
    @Resource
    private BingImageCallablePageProcessor bingImageCallablePageProcessor;
    @Resource
    private BingVedioCallablePageProcessor bingVedioCallablePageProcessor;

    @Test
    public void testBingWebpageCrawler() {
        System.out.println("Start~");
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num, bingWebpageCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println("End~");
    }

    @Test
    public void testBingImageCrawler() {
        System.out.println("Start~");
        Long startTime = System.currentTimeMillis();
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num, bingImageCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("End~");

    }
    @Test
    public void testBingVedioCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword,start,num,bingVedioCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("End~");
    }
}