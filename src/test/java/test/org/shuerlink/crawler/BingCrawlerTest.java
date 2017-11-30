package test.org.shuerlink.crawler;

import org.junit.Test;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BingImageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BingWebpageCrawler;
import us.codecraft.webmagic.Spider;

public class BingCrawlerTest {

    @Test
    public void testBingWebpageCrawler() {
        System.out.println("Start~");
        BingWebpageCrawler bingWebpageCrawler = BingWebpageCrawler.newInstance("可达鸭",0);
        Spider spider = Spider.create(bingWebpageCrawler);
        spider.setEmptySleepTime(100);
        spider.addUrl(bingWebpageCrawler.getUrl()).run();
        System.out.println(bingWebpageCrawler.getWebPageResults());
        System.out.println("End~");
    }

    @Test
    public void testBingImageCrawler() {
        System.out.println("Start~");
        Long startTime = System.currentTimeMillis();
        BingImageCrawler bingImageCrawler = BingImageCrawler.newInstance("可达鸭",0);
        Spider spider = Spider.create(bingImageCrawler);
        spider.setEmptySleepTime(100);
        spider.addUrl(bingImageCrawler.getUrl()).run();
        System.out.println(bingImageCrawler.getImageResults());
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");

    }
}