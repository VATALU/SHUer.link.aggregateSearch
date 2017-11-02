package org.shuerlink.crawlerImpl;

import org.shuerlink.crawler.PaperCrawler;
import org.shuerlink.model.PaperResult;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class ZhiwangCrawlerImpl implements PaperCrawler{
	@Override
	public LinkedList<PaperResult> getPaperReult(String keyword, int start, int num) {
		return null;
	}
}
