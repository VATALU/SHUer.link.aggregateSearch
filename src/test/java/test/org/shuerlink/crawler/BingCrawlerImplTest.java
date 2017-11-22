package test.org.shuerlink.crawler;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawlerImpl.BingCrawlerImpl;

public class BingCrawlerImplTest {

    @Test
    public void testBingWebpageCrawler() {
        System.out.println("Start~");
        System.out.println(new BingCrawlerImpl().getWebPageResult("可达鸭", 0, 10));
        System.out.println("End~");
    }

    @Test
    public void testBingImageCrawler() {
        System.out.println("Start~");
        Long startTime = System.currentTimeMillis();
        System.out.println(new BingCrawlerImpl().getImageResult("idea", 0));
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");

    }
}