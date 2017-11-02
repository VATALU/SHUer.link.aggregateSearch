package org.shuerlink.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.shuerlink.crawlerImpl.*;
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
    @Resource
    private BaiduCrawlerImpl baiduCrawler;
    @Resource
    private GoogleCrawlerImpl googleCrawler;
    @Resource
    private BingCrawlerImpl bingCrawler;
    @Resource
    private DoubanCrawlerImpl doubanCrawler;
    @Resource
    private BilibiliCrawlerImpl bilibiliCrawler;
    @Resource
    private IqiyiCrawlerImpl iqiyiCrawler;
    @Resource
    private QQMusicCrawlerImpl qqMusicCrawler;
    @Resource
    private WangyiyunCrawlerImpl wangyiyunCrawler;
    @Resource
    private XiamiMusicCrawlerImpl xiamiMusicCrawler;
    @Resource
    private ZhiwangCrawlerImpl zhiwangCrawler;
    @Resource
    private YoukuCrawlerImpl youkuCrawler;

    /*
    * 搜索网页
    * */
    @Override
    public LinkedList<WebPageResult> searchWebPage(String keyword, int start, int num) {
         /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<WebPageResult>>> resultArrayList = new ArrayList<Future<LinkedList<WebPageResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getWebPageResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> googleCrawler.getWebPageResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> bingCrawler.getWebPageResult(keyword,start,num)));

        /*
        * 获取线程返回值
        * */
        LinkedList<WebPageResult> results = new LinkedList<WebPageResult>();
        for (Future<LinkedList<WebPageResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        results = new LinkedList<WebPageResult>(new LinkedHashSet<WebPageResult>(results));
        /*
        * 排序
        * */
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        return results;
    }

    /*
    * 搜索照片
    * */
    @Override
    public LinkedList<ImageResult> searchImage(String keyword, int start, int num) {
         /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<ImageResult>>> resultArrayList = new ArrayList<Future<LinkedList<ImageResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getImageResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> googleCrawler.getImageResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> bingCrawler.getImageResult(keyword,start,num)));

        /*
        * 获取线程返回值
        * */
        LinkedList<ImageResult> results = new LinkedList<ImageResult>();
        for (Future<LinkedList<ImageResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        results = new LinkedList<ImageResult>(new LinkedHashSet<ImageResult>(results));
        /*
        * 排序
        * */
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        return results;
    }

    /*
    * 搜索视频
    * */
    @Override
    public LinkedList<VedioResult> searchVedio(String keyword, int start, int num) {
        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<VedioResult>>> resultArrayList = new ArrayList<Future<LinkedList<VedioResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getVedioResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> googleCrawler.getVedioResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> bingCrawler.getVedioResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> bilibiliCrawler.getVedioResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> youkuCrawler.getVedioResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> iqiyiCrawler.getVedioResult(keyword,start,num)));

        /*
        * 获取线程返回值
        * */
        LinkedList<VedioResult> results = new LinkedList<VedioResult>();
        for (Future<LinkedList<VedioResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        results = new LinkedList<VedioResult>(new LinkedHashSet<VedioResult>(results));
        /*
        * 排序
        * */
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        return results;
    }

    /*
    * 搜索音乐
    * */
    @Override
    public LinkedList<MusicResult> searchMusic(String keyword, int start, int num) {
        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<MusicResult>>> resultArrayList = new ArrayList<Future<LinkedList<MusicResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getMusicResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> qqMusicCrawler.getMusicResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> xiamiMusicCrawler.getMusicResult(keyword,start,num)));
        resultArrayList.add(taskExecutor.submit(() -> wangyiyunCrawler.getMusicResult(keyword,start,num)));

        /*
        * 获取线程返回值
        * */
        LinkedList<MusicResult> results = new LinkedList<MusicResult>();
        for (Future<LinkedList<MusicResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        results = new LinkedList<MusicResult>(new LinkedHashSet<MusicResult>(results));
        /*
        * 排序
        * */
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        return results;
    }

    /*
    * 搜索书籍
    * */
    @Override
    public LinkedList<BookResult> searchBook(String keyword, int start, int num) {
         /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<BookResult>>> resultArrayList = new ArrayList<Future<LinkedList<BookResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> doubanCrawler.getBookResult(keyword,start,num)));

        /*
        * 获取线程返回值
        * */
        LinkedList<BookResult> results = new LinkedList<BookResult>();
        for (Future<LinkedList<BookResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        results = new LinkedList<BookResult>(new LinkedHashSet<BookResult>(results));
        /*
        * 排序
        * */
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        return results;
    }

    /*
    * 搜索论文
    * */
    @Override
    public LinkedList<PaperResult> searchPaper(String keyword, int start, int num) {
          /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<PaperResult>>> resultArrayList = new ArrayList<Future<LinkedList<PaperResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> zhiwangCrawler.getPaperReult(keyword,start,num)));

        /*
        * 获取线程返回值
        * */
        LinkedList<PaperResult> results = new LinkedList<PaperResult>();
        for (Future<LinkedList<PaperResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*
        *  URL去重
        */
        results = new LinkedList<PaperResult>(new LinkedHashSet<PaperResult>(results));
        /*
        * 排序
        * */
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        return results;
    }

}
