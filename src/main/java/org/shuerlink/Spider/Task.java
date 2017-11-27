package org.shuerlink.Spider;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.model.Result;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Task<T> {

    public LinkedList<T> execute(Spider<T> spider) {
        LinkedList<Crawler<T>> crawlers = spider.getCrawlers();
        ThreadPoolTaskExecutor taskExecutor =  spider.getExecutor();

        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<T>>> resultArrayList = new ArrayList<>();
        for(Crawler<T> crawler:crawlers){
            resultArrayList.add(taskExecutor.submit(()->crawler.process(getConnect(crawler))));
        }
        /*
        * 获取线程返回值
        * */
        LinkedList<T> results = new LinkedList<>();
        for (Future<LinkedList<T>> f : resultArrayList) {
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
        results = new LinkedList<>(new LinkedHashSet<>(results));
        return results;
    }

    public Document getConnect(Crawler<T> crawler) {
        Site site = crawler.getSite();
        String url = crawler.getUrl();
        String userAgent = site.getUserAgent();
        int timeOut = site.getTimeOut();
        Map<String, String> cookies = site.getCookies();
        Document document = null;
        for (int i = 0; i < site.getRetryTimes() + 1; i++) {
            try {
                System.out.println(url);
                Response response = Jsoup.connect(url).userAgent(userAgent).timeout(timeOut).cookies(cookies).method(Method.GET).execute();
                Integer statusCode = site.getAcceptStatCode();
                if (response.statusCode() == statusCode) {
                    document = response.parse();
                    break;
                } else {
                    Thread.sleep(site.getRetrySleepTime());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return document;
    }


}
