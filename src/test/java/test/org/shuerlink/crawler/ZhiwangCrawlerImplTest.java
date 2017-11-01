package test.org.shuerlink.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawlerImpl.ZhiwangCrawlerImpl;

public class ZhiwangCrawlerImplTest {
    @Test
    public void testBaiduCrawler() throws UnsupportedEncodingException {

        System.out.println("Start:");
        System.out.println(new ZhiwangCrawlerImpl().getPaperReult("王宝强"));
    }
}
