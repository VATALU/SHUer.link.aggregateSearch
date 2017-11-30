package org.shuerlink.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCrawler;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BingImageCrawler;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.GoogleImageCrawler;
import org.shuerlink.crawlerImpl.VedioCrawlerImpl.BaiduVedioCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BingWebpageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.GoogleWebpageCrawler;
import org.shuerlink.model.*;
import org.shuerlink.service.SearchService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

/**
 * @author VATALU
 * @version 0.1
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    /*
    * 搜索网页
    * */
    @Override
    public LinkedList<WebPageResult> getWebpage(String keyword, int start, int num) {
        BaiduWebpageCrawler baiduWebpageCrawler = BaiduWebpageCrawler.newInstance(keyword, start, num);
        BingWebpageCrawler bingWebpageCrawler = BingWebpageCrawler.newInstance(keyword, start);
        GoogleWebpageCrawler googleWebpageCrawler = GoogleWebpageCrawler.newInstance(keyword, start, num);
         /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<WebPageResult>>> futureArrayList = new ArrayList<>();
        futureArrayList.add(taskExecutor.submit(() -> {
            Spider spider = Spider.create(baiduWebpageCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(baiduWebpageCrawler.getUrl()).run();
            return baiduWebpageCrawler.getWebPageResults();
        }));
        futureArrayList.add(taskExecutor.submit(() -> {
            Spider spider = Spider.create(bingWebpageCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(bingWebpageCrawler.getUrl()).run();
            return bingWebpageCrawler.getWebPageResults();
        }));
        futureArrayList.add(taskExecutor.submit(()->{
            Spider spider = Spider.create(googleWebpageCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(googleWebpageCrawler.getUrl()).run();
            return googleWebpageCrawler.getWebPageResults();
        }));
        /*
        * 获取线程返回值
        * */
        LinkedList<WebPageResult> webPageResults = new LinkedList<WebPageResult>();
        for (Future<LinkedList<WebPageResult>> f : futureArrayList) {
            try {
                webPageResults.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        webPageResults = new LinkedList<WebPageResult>(new LinkedHashSet<WebPageResult>(webPageResults));
        /*
        * 排序
        * */
        webPageResults.sort((t1, t2) -> (t1.compareTo(t2)));
        return webPageResults;
    }

    /*
    * 搜索照片
    * */
    @Override
    public LinkedList<ImageResult> getImage(String keyword, int start) {
        BaiduImageCrawler baiduImageCrawler = BaiduImageCrawler.newInstance(keyword, start);
        BingImageCrawler bingImageCrawler = BingImageCrawler.newInstance(keyword, start);
        GoogleImageCrawler googleImageCrawler = GoogleImageCrawler.newInstance(keyword);

        ArrayList<Future<LinkedList<ImageResult>>> futureArrayList = new ArrayList<>();
        futureArrayList.add(taskExecutor.submit(() -> {
            Spider spider = Spider.create(baiduImageCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(baiduImageCrawler.getUrl()).run();
            return baiduImageCrawler.getImageResults();
        }));
        futureArrayList.add(taskExecutor.submit(() -> {
            Spider spider = Spider.create(bingImageCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(bingImageCrawler.getUrl()).run();
            return bingImageCrawler.getImageResults();
        }));
        futureArrayList.add(taskExecutor.submit(() -> {
            Spider spider = Spider.create(googleImageCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(googleImageCrawler.getUrl()).run();
            return googleImageCrawler.getImageResults();
        }));

         /*
        * 获取线程返回值
        * */
        LinkedList<ImageResult> imageResults = new LinkedList<>();
        for (Future<LinkedList<ImageResult>> f : futureArrayList) {
            try {
                imageResults.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *url去重
         */
        imageResults = new LinkedList<>(new LinkedHashSet<>(imageResults));
        /*
        *url排序
         */
        imageResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return imageResults;
    }

    /*
    * 搜索视频
    * */
    @Override
    public LinkedList<VedioResult> getVedio(String keyword, int start, int num) {
        LinkedList<VedioResult> vedioResults = null;
        BaiduVedioCrawler baiduVedioCrawler = BaiduVedioCrawler.newInstance(keyword, start);

        ArrayList<Future<LinkedList<VedioResult>>> futureArrayList = new ArrayList<>();
        futureArrayList.add(taskExecutor.submit(() -> {
            Spider spider = Spider.create(baiduVedioCrawler);
            spider.setEmptySleepTime(100);
            spider.addUrl(baiduVedioCrawler.getUrl()).run();
            return baiduVedioCrawler.getVedioResults();
        }));

         /*
        * 获取线程返回值
        * */
        LinkedList<VedioResult> webPageResults = new LinkedList<VedioResult>();
        for (Future<LinkedList<VedioResult>> f : futureArrayList) {
            try {
                webPageResults.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        vedioResults = new LinkedList<>(new LinkedHashSet<>(vedioResults));

        vedioResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return vedioResults;
    }

}
