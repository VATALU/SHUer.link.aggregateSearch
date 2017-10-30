package test.org.shuerlink.crawler;

import org.junit.Test;
import org.shuerlink.crawler.GoogleCrawler;

import java.io.IOException;

public class GoogleCrawlerTest {
    @Test
    public void testGoogleCrawler() {
        System.out.println("Start:");
        System.out.println(new GoogleCrawler().start("可达鸭"));
        System.out.println("End~");
    }
}
