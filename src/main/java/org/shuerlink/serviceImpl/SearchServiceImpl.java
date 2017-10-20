package org.shuerlink.serviceImpl;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.shuerlink.crawler.BaiduCrawler;
import org.shuerlink.crawler.BingCrawler;
import org.shuerlink.service.SearchService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;


	public String search(String content) {
		return addCrawlerTask(content);
	}

	private String addCrawlerTask(final String content) {
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
			results.add(taskExecutor.submit(new Callable<String>() {
				public String call() throws Exception {
					return new BaiduCrawler().start(content);
				}
			}));
			results.add(taskExecutor.submit(new Callable<String>() {
				public String call() throws Exception {
					return new BingCrawler().start(content);
				}
			}));
			
		StringBuffer sb = new StringBuffer();
		for(Future<String> f : results) {
			try {
				sb.append(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
