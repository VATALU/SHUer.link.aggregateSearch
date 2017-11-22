package test.org.shuerlink.crawler;


import org.junit.Test;
import org.shuerlink.crawlerImpl.BaiduCrawlerImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiduCrawlerImplTest {
    @Test
    public void testBaiduCrawler() {
        System.out.println("Start:");
        System.out.println(new BaiduCrawlerImpl().getWebPageResult("德鲁伊", 0, 10));
        System.out.println("End~");
    }

    @Test
    public void testBaiduImageCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        System.out.println(new BaiduCrawlerImpl().getImageResult("可达鸭", 0, 10));
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }
}
