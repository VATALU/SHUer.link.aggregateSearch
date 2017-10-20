package org.shuerlink.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaiduCrawler {
	private static String baidu = "http://www.baidu.com/s?wd=";
	public String start(String content) throws IOException {
		// System.out.println(Thread.currentThread()+"开始运行");
		// Long startTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("百度\n");
		Document doc = Jsoup.connect(baidu + content).userAgent("Mozilla").timeout(3000).get();
		Elements results = doc.select("div.result.c-container");
		for (Element result : results) {
			Elements piece = result.select("h3");
			sb.append(piece.text());
			sb.append("\n");
			sb.append(piece.select("a[href]").attr("href"));
			sb.append("\n");
			sb.append(result.select(".c-abstract").text());
			sb.append("\n\n");
		}
		return sb.toString();
		// System.out.println(Thread.currentThread()+":运行结束");
		// return Thread.currentThread().getName()+"：使用时间"+(startTime +
		// System.currentTimeMillis()) + "\n";
	}
}
