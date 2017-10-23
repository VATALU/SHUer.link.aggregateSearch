package org.shuerlink.crawler;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.SearchResult;

public class BaiduCrawler {
	private static String baidu = "http://www.baidu.com/s?wd=";

	public LinkedList<SearchResult> start(String keyword) throws IOException {
		LinkedList<SearchResult> resultList = new LinkedList<SearchResult>();
		Document doc = Jsoup.connect(baidu + keyword).userAgent("Mozilla").timeout(3000).get();
		
//		Elements specialResults = doc.select("div.result-op.c-container.xpath-log");
//		for(Element specialResult : specialResults) {
//			Elements piece = specialResult.select("h3");
//			piece.attr("href");
//			piece.text();
//		}
		
		Elements results = doc.select("div.result.c-container");
		for (Element result : results) {
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchEngine("百度");
			Elements piece = result.select("h3");
			searchResult.setTitle(piece.text());
			searchResult.setTitleURL(piece.select("a[href]").attr("href"));
			searchResult.set_abstract(result.select(".c-abstract").text());
			resultList.add(searchResult);
		}
		return resultList;
	}
}
