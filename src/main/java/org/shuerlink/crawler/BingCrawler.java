package org.shuerlink.crawler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.SearchResult;

public class BingCrawler {
	private static String bing = "https://www.bing.com/search?q=";

	public LinkedList<SearchResult> start(String keyword) throws IOException {
		LinkedList<SearchResult> resultList = new LinkedList<SearchResult>();
		keyword = URLEncoder.encode(keyword, "UTF-8");
		Document doc = Jsoup.connect(bing + keyword).userAgent("Mozilla").timeout(3000).get();
		Elements results = doc.select("ol#b_results li.b_algo");
		for (Element result : results) {
			Elements s = result.select("h2");
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchEngine("必应");
			searchResult.setTitle(s.text());
			searchResult.setTitleURL(s.select("a[href]").attr("href"));
			searchResult.set_abstract(result.select("p").text());
			resultList.add(searchResult);
		}
		return resultList;
	}
}
