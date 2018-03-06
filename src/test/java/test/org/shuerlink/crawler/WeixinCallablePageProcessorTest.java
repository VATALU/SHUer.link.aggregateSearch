package test.org.shuerlink.crawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shuerlink.crawler.CallableSpider;
import org.shuerlink.crawler.crawlerImpl.ShareCrawlerImpl.WeixinShareCallablePageProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})//加载spring配置文件
public class WeixinCallablePageProcessorTest {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private WeixinShareCallablePageProcessor weixinShareCallablePageProcessor;

    @Test
    public void testWeixinShareCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        String keyword = "可达鸭";
        int start = 0;
        int num = 10;
        CallableSpider callableSpider = CallableSpider.newInstance(keyword,start,num,weixinShareCallablePageProcessor).setThreadPoolTask(threadPoolTaskExecutor);
        System.out.println(callableSpider.call());
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println("End~");
    }
}
