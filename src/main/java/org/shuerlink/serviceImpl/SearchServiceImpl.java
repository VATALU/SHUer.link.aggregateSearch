package org.shuerlink.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.shuerlink.crawler.BaiduCrawler;
import org.shuerlink.crawler.BingCrawler;
import org.shuerlink.crawler.GoogleCrawler;
import org.shuerlink.model.TextResult;
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

    public LinkedList<TextResult> search(String keyword) {
        return addCrawlerTask(keyword);
    }

    private LinkedList<TextResult> addCrawlerTask(final String keyword) {
        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<TextResult>>> resultArrayList = new ArrayList<Future<LinkedList<TextResult>>>();
        resultArrayList.add(taskExecutor.submit(() -> new BaiduCrawler().start(keyword)));
        resultArrayList.add(taskExecutor.submit(()-> new GoogleCrawler().start(keyword)));
        resultArrayList.add(taskExecutor.submit(()->new BingCrawler().start(keyword)));

        //获取返回信息
        Long analysisTime = System.currentTimeMillis();
        LinkedList<TextResult> results = new LinkedList<TextResult>();
        for (Future<LinkedList<TextResult>> f : resultArrayList) {
            try {
                results.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("解析耗时：" + (System.currentTimeMillis() - analysisTime));
        // URL去重
        Long urlTime = System.currentTimeMillis();
        results = new LinkedList<TextResult>(new LinkedHashSet<TextResult>(results));
        System.out.println("url去重耗时：" + (System.currentTimeMillis() - urlTime));
        // 排序
        Long sortTime = System.currentTimeMillis();
        results.sort((tr1, tr2) -> (tr1.compareTo(tr2)));
        System.out.println("排序耗时：" + (System.currentTimeMillis() - sortTime));
        return results;
    }

}
