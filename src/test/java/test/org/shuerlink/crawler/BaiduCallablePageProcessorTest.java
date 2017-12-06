package test.org.shuerlink.crawler;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.shuerlink.crawler.CallableSpider;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCallablePageProcessor;
import org.shuerlink.crawlerImpl.VedioCrawlerImpl.BaiduVedioCallablePageProcessor;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCallablePageProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})//加载spring配置文件
public class BaiduCallablePageProcessorTest {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private BaiduWebpageCallablePageProcessor baiduWebpageCallablePageProcessor;
    @Resource
    private BaiduImageCallablePageProcessor baiduImageCallablePageProcessor;
    @Resource
    private BaiduVedioCallablePageProcessor baiduVedioCallablePageProcessor;

    @Test
    public void testBaiduWebpageCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword,start,num,baiduWebpageCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("End~");
    }

    @Test
    public void testBaiduImageCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword,start,num,baiduImageCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }

    @Test
    public void testBaiduVedioCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword,start,num,baiduVedioCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("End~");
    }
}
