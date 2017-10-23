package org.shuerlink.crawler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.SearchResult;

public class BingCrawler {
	private static String bing = "https://www.bing.com/search?q=";

	public ArrayList<SearchResult> start(String keyword) throws IOException {
		ArrayList<SearchResult> resultList = new ArrayList<SearchResult>();
		keyword = URLEncoder.encode(keyword,"UTF-8");
		StringBuffer sb = new StringBuffer("��Ӧ\n");
		Document doc = Jsoup.connect(bing + keyword).userAgent("Mozilla").timeout(3000).get();
		Elements results = doc.select("ol#b_results li.b_algo");
		for (Element result : results) {
			Elements s = result.select("h2");
			sb.append(s.text());
			sb.append("\n");
			sb.append(s.select("a[href]").attr("href"));
			sb.append("\n");
			sb.append(result.select("p").text());
			sb.append("\n\n");
		}
		return resultList;
	}
}
