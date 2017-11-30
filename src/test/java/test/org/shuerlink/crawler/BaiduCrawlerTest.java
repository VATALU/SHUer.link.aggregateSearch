package test.org.shuerlink.crawler;


import org.junit.Test;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCrawler;
import org.shuerlink.crawlerImpl.VedioCrawlerImpl.BaiduVedioCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import us.codecraft.webmagic.Spider;

public class BaiduCrawlerTest {

    @Test
    public void testBaiduWebpageCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        BaiduWebpageCrawler baiduWebpageCrawler = BaiduWebpageCrawler.newInstance("可达鸭", 0, 10);
        Spider spider = Spider.create(baiduWebpageCrawler);
        spider.setEmptySleepTime(100);
        spider.addUrl(baiduWebpageCrawler.getUrl()).run();
        System.out.println(baiduWebpageCrawler.getWebPageResults());
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }

    @Test
    public void testBaiduImageCrawler() {
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        BaiduImageCrawler baiduImageCrawler = BaiduImageCrawler.newInstance("可达鸭",0);
        Spider spider = Spider.create(baiduImageCrawler);
        spider.setEmptySleepTime(100);
        spider.addUrl(baiduImageCrawler.getUrl()).run();
        System.out.println(baiduImageCrawler.getImageResults());
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }

    @Test
    public void testBaiduVedioCrawler(){
        System.out.println("Start:");
        Long startTime = System.currentTimeMillis();
        BaiduVedioCrawler baiduVedioCrawler = BaiduVedioCrawler.newInstance("可达鸭",0);
        Spider spider = Spider.create(baiduVedioCrawler);
        spider.setEmptySleepTime(100);
        spider.addUrl(baiduVedioCrawler.getUrl()).run();
        System.out.println(baiduVedioCrawler.getVedioResults());
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println("End~");
    }
}
