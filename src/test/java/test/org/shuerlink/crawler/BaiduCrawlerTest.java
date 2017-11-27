package test.org.shuerlink.crawler;


import org.junit.Test;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.Spider.Spider;
import org.shuerlink.Spider.Task;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCrawler;
import org.shuerlink.crawlerImpl.VedioCrawlerImpl.BaiduVedioCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;

import java.util.LinkedList;
import java.util.Random;

public class BaiduCrawlerTest {
    @Test
    public void testBaiduCrawler() {
        System.out.println("Start:");
        BaiduWebpageCrawler baiduWebpageCrawler = BaiduWebpageCrawler.newInstance("可达鸭", 0, 10);
        System.out.println(baiduWebpageCrawler.process(new Task<WebPageResult>().getConnect(baiduWebpageCrawler)));
        System.out.println("End~");
    }

    @Test
    public void testBaiduImageCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        BaiduImageCrawler baiduImageCrawler = BaiduImageCrawler.newInstance("可达鸭",0);
        System.out.println(baiduImageCrawler.process(new Task<ImageResult>().getConnect(baiduImageCrawler)));
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }

    @Test
    public void testBaiduVedioCrawler(){
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        BaiduVedioCrawler baiduVedioCrawler = BaiduVedioCrawler.newInstance("可达鸭",0);
        System.out.println(baiduVedioCrawler.process(new Task<VedioResult>().getConnect(baiduVedioCrawler)));
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }
}
