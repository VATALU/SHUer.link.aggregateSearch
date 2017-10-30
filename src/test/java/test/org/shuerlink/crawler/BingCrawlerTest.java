package test.org.shuerlink.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawler.BingCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BingCrawlerTest {
    private static final Logger logger = LoggerFactory.getLogger(BingCrawlerTest.class);

    @Test
    public void testBaiduCrawler() throws UnsupportedEncodingException {
        logger.debug("Start~");
        System.out.println(new BingCrawler().start("可达鸭"));
        System.out.println("End~");
    }

}