package test.org.shuerlink.crawler;


import org.junit.Test;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.Spider.Spider;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCrawler;

import java.util.LinkedList;
import java.util.Random;

public class BaiduWebpageCrawlerTest {
    @Test
    public void testBaiduCrawler() {
        System.out.println("Start:");
        BaiduWebpageCrawler baiduWebpageCrawler = new BaiduWebpageCrawler();
        String keyword = "可达鸭";
        baiduWebpageCrawler.getSite().addQueryParameter("wd",keyword).addQueryParameter("pn","0").addQueryParameter("rn","10");
        LinkedList<Crawler> crawlers = new LinkedList<Crawler>();
        crawlers.add(baiduWebpageCrawler);
        System.out.println("End~");
    }

//    @Test
//    public void testBaiduImageCrawler() {
//        System.out.println("Start:");
//        Long startTime = System.currentTimeMillis();
//        System.out.println(new BaiduImageCrawler().getImageResults("可达鸭", 0));
//        System.out.println(System.currentTimeMillis()-startTime);
//        System.out.println("End~");
//    }
//
//    @Test
//    public void testFrequentlyRequest(){
//        System.out.println("Start:");
//        String[] part = {"可","达","鸭","for","each","idea","is","coco","一点点"};
//
//        int i = 0;
//        Random random = new Random(47);
//        while(i<1000){
//            System.out.println(i++);
//            StringBuffer stringbuffer = new StringBuffer();
//            stringbuffer.append(part[random.nextInt(8)]);
//            stringbuffer.append(part[random.nextInt(8)]);
//            stringbuffer.append(part[random.nextInt(8)]);
//            BaiduWebpageCrawler baiduCrawler = new BaiduWebpageCrawler();
//            baiduCrawler.getWebpageResults(stringbuffer.toString(),0,10);
//        }
//        System.out.println("End~");
//    }

}
