package test.org.shuerlink.crawler;

import java.io.IOException;

import org.junit.Test;
import org.shuerlink.crawler.BaiduCrawler;

public class BaiduCrawlerTest {
    @Test
    public void testBaiduCrawler() {
        System.out.println("Start:");
        System.out.println(new BaiduCrawler().start("idea吧"));
        System.out.println("End~");
    }
}
