package test.org.shuerlink.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.shuerlink.crawlerImpl.GoogleCrawlerImpl;

import java.io.IOException;

public class GoogleCrawlerImplTest {
    @Test
    public void testGoogleCrawler() {
        System.out.println("Start:");
        System.out.println(new GoogleCrawlerImpl().getWebPageResult("可达鸭", 0, 10));
        System.out.println("End~");
    }

    @Test
    public void testGoogleImageCrawler(){
        System.out.println("Start:");
        System.out.println(new GoogleCrawlerImpl().getImageResult("idea",0));
        System.out.println("End~");
    }
}
