package test.org.shuerlink.crawler;

import org.junit.Test;
import org.shuerlink.crawlerImpl.GoogleCrawlerImpl;


public class GoogleCrawlerImplTest {
    @Test
    public void testGoogleCrawler() {
        System.out.println("Start:");
        System.out.println(new GoogleCrawlerImpl().getWebPageResult("可达鸭", 0, 10));
        System.out.println("End~");
    }
}
