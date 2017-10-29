package test.org.shuerlink.crawler;

import org.junit.Test;
import org.shuerlink.crawler.GoogleCrawler;

import java.io.IOException;

public class GoogleCrawlerTest {
    @Test
    public void testGoogleCrawler() {
        System.out.println("Start:");
        try {
            System.out.println(new GoogleCrawler().start("大师球"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connect failer");
        }
        System.out.println("End~");
    }
}
