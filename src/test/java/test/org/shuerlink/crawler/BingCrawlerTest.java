package test.org.shuerlink.crawler;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawler.BingCrawler;

public class BingCrawlerTest {

    @Test
    public void testBaiduCrawler() throws UnsupportedEncodingException {
        System.out.println("Start~");
        System.out.println(new BingCrawler().start("可达鸭"));
        System.out.println("End~");
    }

}