package org.shuerlink.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    public LinkedList<Result> getWebpage(String keyword, int start, int num) {
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LinkedList<Result> results = null;
        BaiduWebpageCrawler baiduWebpageCrawler = new BaiduWebpageCrawler();
        baiduWebpageCrawler.getSite().addQueryParameter("wd", keyword)
                .addQueryParameter("pn", String.valueOf(start))
                .addQueryParameter("rn", String.valueOf(num));
        BingWebpageCrawler bingWebpageCrawler = new BingWebpageCrawler();
        bingWebpageCrawler.getSite().addQueryParameter("q", keyword)
                .addQueryParameter("first", String.valueOf(start));
        GoogleWebpageCrawler googleWebpageCrawler = new GoogleWebpageCrawler();
        googleWebpageCrawler.getSite().addQueryParameter("q", keyword)
                .addQueryParameter("start", String.valueOf(start))
                .addQueryParameter("num", String.valueOf(num))
                .addQueryParameter("lr", "lang_zh-CN");

        LinkedList<Crawler> crawlers = new LinkedList<>();
        crawlers.add(baiduWebpageCrawler);
        crawlers.add(bingWebpageCrawler);
        crawlers.add(googleWebpageCrawler);

        results = Spider.newInstance(crawlers, taskExecutor).run();
        return results;
    }

    /*
    * 搜索照片
    * */
    @Override
    public LinkedList<Result> getImage(String keyword, int start) {
        try {
            keyword = URLEncoder.encode(keyword,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LinkedList<Result> results = null;
        BaiduImageCrawler baiduImageCrawler = new BaiduImageCrawler();
        baiduImageCrawler.getSite().addQueryParameter("tn", "baiduimage")
                .addQueryParameter("word", keyword)
                .addQueryParameter("pn", String.valueOf(start));
        BingImageCrawler bingImageCrawler = new BingImageCrawler();
        bingImageCrawler.getSite().addQueryParameter("q", keyword)
                .addQueryParameter("qs", "n")
                .addQueryParameter("form", "QBIR")
                .addQueryParameter("first", String.valueOf(start));
        GoogleImageCrawler googleImageCrawler = new GoogleImageCrawler();
        googleImageCrawler.getSite().addQueryParameter("tbm", "isch")
                .addQueryParameter("q",keyword);

        LinkedList<Crawler> crawlers = new LinkedList<>();
        crawlers.add(baiduImageCrawler);
        crawlers.add(bingImageCrawler);
        crawlers.add(googleImageCrawler);
        results = Spider.newInstance(crawlers,taskExecutor).run();
        return results;
    }

    /*
    * 搜索视频
    * */
    @Override
    public LinkedList<Result> getVedio(String keyword, int start, int num) {
        LinkedList<Result> results = null;
        return results;
    }

    @Override
    public LinkedList<Result> getMusic(String keyword, int start, int num) {
        return null;
    }

    /*
    * 搜索书籍
    * */
    @Override
    public LinkedList<Result> getBook(String keyword, int start, int num) {
        return null;
    }

    /*
    * 搜索论文
    * */
    @Override
    public LinkedList<Result> getPaper(String keyword, int start, int num) {
        return null;
    }

}
