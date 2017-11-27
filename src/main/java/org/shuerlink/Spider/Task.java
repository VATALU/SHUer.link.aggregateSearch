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

public class Task {

    public LinkedList<Result> execute(Spider spider) {
        LinkedList<Crawler> crawlers = spider.getCrawlers();
        ThreadPoolTaskExecutor taskExecutor =  spider.getExecutor();

        /*
        * 添加爬虫线程
		* */
        ArrayList<Future<LinkedList<Result>>> resultArrayList = new ArrayList<Future<LinkedList<Result>>>();
        for(Crawler crawler:crawlers){
            resultArrayList.add(taskExecutor.submit(()->crawler.process(getConnect(crawler.getSite()))));
        }
        /*
        * 获取线程返回值
        * */
        LinkedList<Result> results = new LinkedList<Result>();
        for (Future<LinkedList<Result>> f : resultArrayList) {
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
        results = new LinkedList<Result>(new LinkedHashSet<Result>(results));
        /*
        * 排序
        * */
        results.sort((t1,t2)->(t1.compareTo(t2)));
        return results;
    }

    public Document getConnect(Site site) {
        StringBuffer url = new StringBuffer(site.getUrl());
        Set<String> queryParameterSet = site.getQueryParameter().keySet();
        for (String key : queryParameterSet) {
            url.append("&" + key + "=");
            url.append(site.getQueryParameter().get(key));
        }
        String userAgent = site.getUserAgent();
        int timeOut = site.getTimeOut();
        Map<String, String> cookies = site.getCookies();
        Document document = null;
        for (int i = 0; i < site.getRetryTimes() + 1; i++) {
            try {
                System.out.println(url.toString());
                Response response = Jsoup.connect(url.toString()).userAgent(userAgent).timeout(timeOut).cookies(cookies).method(Method.GET).execute();
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
