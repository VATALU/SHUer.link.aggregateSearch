package test.org.shuerlink.crawler;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawlerImpl.BingCrawlerImpl;

public class BingCrawlerImplTest {

    @Test
    public void testBaiduCrawler() throws UnsupportedEncodingException {
        System.out.println("Start~");
        System.out.println(new BingCrawlerImpl().getWebPageResult("可达鸭", 0, 10));
        System.out.println("End~");
    }

    @Test
    public void testBaiduImageCrawler() {
        System.out.println("Start~");
        System.out.println(new BingCrawlerImpl().getImageResult("可达鸭", 0, 10));
        System.out.println("End~");
    }
}