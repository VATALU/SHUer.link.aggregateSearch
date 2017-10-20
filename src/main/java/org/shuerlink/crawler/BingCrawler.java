package org.shuerlink.crawler;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BingCrawler {
	private static String bing = "https://www.bing.com/search?q=";

	public String start(String content) throws IOException {
		content = URLEncoder.encode(content,"UTF-8");
		StringBuffer sb = new StringBuffer("±ÿ”¶\n");
		Document doc = Jsoup.connect(bing + content).userAgent("Mozilla").timeout(3000).get();
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
		return sb.toString();
	}
}
