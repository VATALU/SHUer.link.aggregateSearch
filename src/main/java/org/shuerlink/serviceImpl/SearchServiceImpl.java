package org.shuerlink.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.shuerlink.crawlerImpl.BaiduCrawlerImpl;
import org.shuerlink.crawlerImpl.BingCrawlerImpl;
import org.shuerlink.crawlerImpl.GoogleCrawlerImpl;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.MusicResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
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

    public LinkedList<WebPageResult> searchWebPage(String keyword) {
         /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<WebPageResult>>> resultArrayList = new ArrayList<Future<LinkedList<WebPageResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getWebPageResult(keyword)));
        resultArrayList.add(taskExecutor.submit(() -> googleCrawler.getWebPageResult(keyword)));
        resultArrayList.add(taskExecutor.submit(() -> bingCrawler.getWebPageResult(keyword)));

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

    @Override
    public LinkedList<ImageResult> searchImage(String keyword) {
         /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<ImageResult>>> resultArrayList = new ArrayList<Future<LinkedList<ImageResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getImageResult(keyword)));
        resultArrayList.add(taskExecutor.submit(() -> googleCrawler.getImageResult(keyword)));
        resultArrayList.add(taskExecutor.submit(() -> bingCrawler.getImageResult(keyword)));

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

    @Override
    public LinkedList<VedioResult> searchVedio(String keyword) {
        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<VedioResult>>> resultArrayList = new ArrayList<Future<LinkedList<VedioResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getVedioResult(keyword)));
        resultArrayList.add(taskExecutor.submit(() -> googleCrawler.getVedioResult(keyword)));
        resultArrayList.add(taskExecutor.submit(() -> bingCrawler.getVedioResult(keyword)));

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

    @Override
    public LinkedList<MusicResult> searchMusic(String keyword) {
        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<MusicResult>>> resultArrayList = new ArrayList<Future<LinkedList<MusicResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> baiduCrawler.getMusicResult(keyword)));

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

}
