package test.org.shuerlink.crawler;


import org.junit.Test;
import org.shuerlink.crawlerImpl.BaiduCrawlerImpl;

public class BaiduCrawlerImplTest {
    @Test
    public void testBaiduCrawler() {
        System.out.println("Start:");
        System.out.println(new BaiduCrawlerImpl().getWebPageResult("idea吧"));
        System.out.println("End~");
    }
}
