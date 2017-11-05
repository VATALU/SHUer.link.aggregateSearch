package test.org.shuerlink.crawler;


import org.junit.Test;
import org.shuerlink.crawlerImpl.BaiduCrawlerImpl;

public class BaiduCrawlerImplTest {
    @Test
    public void testBaiduCrawler() {
        System.out.println("Start:");
        System.out.println(new BaiduCrawlerImpl().getWebPageResult("德鲁伊", 0, 10));
        System.out.println("End~");
    }

    @Test
    public void testBaiduImageCrawler() {
        System.out.println("Start:");
        System.out.println(new BaiduCrawlerImpl().getImageResult("idea", 0, 10));
        System.out.println("End~");
    }
}
