package test.org.shuerlink.crawler;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawlerImpl.BingCrawlerImpl;

public class BingCrawlerImplTest {

    @Test
    public void testBaiduCrawler() throws UnsupportedEncodingException {
        System.out.println("Start~");
        System.out.println(new BingCrawlerImpl().getWebPageResult("可达鸭"));
        System.out.println("End~");
    }

}