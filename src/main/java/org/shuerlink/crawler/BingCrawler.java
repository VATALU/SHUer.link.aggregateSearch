package org.shuerlink.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

import net.didion.jwnl.data.Exc;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.TextResult;
import org.shuerlink.util.AssessScore;

public class BingCrawler {
    private static String bing = "https://www.bing.com/search?q=";

    public LinkedList<TextResult> start(String keyword) {
        LinkedList<TextResult> resultList = null;
        try {
            resultList = new LinkedList<TextResult>();
            keyword = URLEncoder.encode(keyword, "UTF-8");
            Long getConnect = System.currentTimeMillis();
            Document doc = Jsoup.connect(bing + keyword).userAgent("Mozilla").timeout(30000).get();
            System.out.println("bing获取链接" + (System.currentTimeMillis() - getConnect));
            Elements results = doc.select(".b_algo");
            int i = 1;
            for (Element result : results) {
                Elements s = result.select("h2");
                TextResult textResult = new TextResult();
                textResult.setSearchEngine("必应搜索");
                textResult.setTitle(s.text());
                textResult.setTitleURL(s.select("a[href]").attr("href"));
                textResult.setDiscription(result.select("p").text());
                textResult.setGrade(AssessScore.assess(i++, "bing", textResult.getTitle(), keyword));
                resultList.add(textResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
