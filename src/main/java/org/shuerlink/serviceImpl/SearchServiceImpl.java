package org.shuerlink.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.shuerlink.crawler.BaiduCrawler;
import org.shuerlink.crawler.BingCrawler;
import org.shuerlink.model.SearchResult;
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

	public LinkedList<SearchResult> search(String keyword) {
		return addCrawlerTask(keyword);
	}

	private LinkedList<SearchResult> addCrawlerTask(final String keyword) {
		ArrayList<Future<LinkedList<SearchResult>>> resultArrayList = new ArrayList<Future<LinkedList<SearchResult>>>();
		resultArrayList.add(taskExecutor.submit(new Callable<LinkedList<SearchResult>>() {
			public LinkedList<SearchResult> call() throws Exception {
				return new BaiduCrawler().start(keyword);
			}
		}));
		resultArrayList.add(taskExecutor.submit(new Callable<LinkedList<SearchResult>>() {
			public LinkedList<SearchResult> call() throws Exception {
				return new BingCrawler().start(keyword);
			}
		}));

		LinkedList<SearchResult> results = new LinkedList<SearchResult>();
		for (Future<LinkedList<SearchResult>> f : resultArrayList) {
			try {
						results.addAll(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		// URL去重
		results = new LinkedList<SearchResult>(new LinkedHashSet<SearchResult>(results));
		// 排序
		Collections.sort(results, new Comparator<SearchResult>() {
			@Override
			public int compare(SearchResult o1, SearchResult o2) {
				return o1.compareTo(o2);
			}

		});
		return results;
	}

}
