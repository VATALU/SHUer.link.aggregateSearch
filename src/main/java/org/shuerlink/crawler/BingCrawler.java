package org.shuerlink.crawler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.TextResult;

public class BingCrawler {
    private static String bing = "https://www.bing.com/search?q=";

    public LinkedList<TextResult> start(String keyword) throws IOException {
        LinkedList<TextResult> resultList = new LinkedList<TextResult>();
        keyword = URLEncoder.encode(keyword, "UTF-8");
        Long getConnect = System.currentTimeMillis();
        Document doc = Jsoup.connect(bing + keyword).userAgent("Mozilla").timeout(3000).get();
        System.out.println("bing获取链接" + (System.currentTimeMillis() - getConnect));
        Elements results = doc.select("ol#b_results li.b_algo");
        for (Element result : results) {
            Elements s = result.select("h2");
            TextResult textResult = new TextResult();
            textResult.setSearchEngine("必应");
            textResult.setTitle(s.text());
            textResult.setTitleURL(s.select("a[href]").attr("href"));
            textResult.set_abstract(result.select("p").text());
            resultList.add(textResult);
        }
        return resultList;
    }
}
