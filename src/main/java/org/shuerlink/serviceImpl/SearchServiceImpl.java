package org.shuerlink.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.annotation.Resource;

import org.shuerlink.Spider.Crawler;
import org.shuerlink.Spider.Spider;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BaiduImageCrawler;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.BingImageCrawler;
import org.shuerlink.crawlerImpl.ImageCrawlerImpl.GoogleImageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.BingWebpageCrawler;
import org.shuerlink.crawlerImpl.WebpageCrawlerImpl.GoogleWebpageCrawler;
import org.shuerlink.model.*;
import org.shuerlink.service.SearchService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

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
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LinkedList<WebPageResult> webPageResults = null;
        BaiduWebpageCrawler baiduWebpageCrawler = BaiduWebpageCrawler.newInstance(keyword, start, num);
        BingWebpageCrawler bingWebpageCrawler = BingWebpageCrawler.newInstance(keyword, start);
        GoogleWebpageCrawler googleWebpageCrawler = GoogleWebpageCrawler.newInstance(keyword, start, num);

        LinkedList<Crawler<WebPageResult>> crawlers = new LinkedList<>();
        crawlers.add(baiduWebpageCrawler);
        crawlers.add(bingWebpageCrawler);
//        crawlers.add(googleWebpageCrawler);

        Spider<WebPageResult> spider = new Spider<WebPageResult>(crawlers, taskExecutor);
        webPageResults = spider.run();
        /*
        *  URL去重
        */
        webPageResults = new LinkedList<>(new LinkedHashSet<>(webPageResults));
        /*
        *排序
         */
        webPageResults.sort((t1, t2) -> (t1.compareTo(t2)));
        return webPageResults;
    }

    /*
    * 搜索照片
    * */
    @Override
    public LinkedList<ImageResult> getImage(String keyword, int start) {
        LinkedList<ImageResult> imageResults = null;
        BaiduImageCrawler baiduImageCrawler = BaiduImageCrawler.newInstance(keyword, start);
        BingImageCrawler bingImageCrawler = BingImageCrawler.newInstance(keyword, start);
        GoogleImageCrawler googleImageCrawler = GoogleImageCrawler.newInstance(keyword);

        LinkedList<Crawler<ImageResult>> crawlers = new LinkedList<>();
        crawlers.add(baiduImageCrawler);
        crawlers.add(bingImageCrawler);
//        crawlers.add(googleImageCrawler);
        Spider<ImageResult> spider = new Spider<ImageResult>(crawlers, taskExecutor);
        imageResults = spider.run();
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
        return null;
    }

    @Override
    public LinkedList<MusicResult> getMusic(String keyword, int start, int num) {
        return null;
    }

    /*
    * 搜索书籍
    * */
    @Override
    public LinkedList<BookResult> getBook(String keyword, int start, int num) {
        return null;
    }

}
