package org.shuerlink.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.TextResult;
import org.shuerlink.util.AssessScore;

import java.util.LinkedList;

public class GoogleCrawler {
    public static final String google = "http://g.shuer.link/search?q=";

    public LinkedList<TextResult> start(String keyword) {
        LinkedList<TextResult> resultList = null;
        try {
            resultList = new LinkedList<TextResult>();
            Document doc = Jsoup.connect(google + keyword).userAgent("Mozilla").timeout(4000).get();
            Elements results = doc.select("div.g");
            int i = 1;
            for (Element result : results) {
                TextResult textResult = new TextResult();
                textResult.setSearchEngine("谷歌搜索");
                Elements piece = result.select("h3");
                textResult.setTitle(piece.text());
                String titleUrl = piece.select("a[href]").attr("href");
                textResult.setDiscription(result.select("span.st").text());
                titleUrl = titleUrl.substring(7, titleUrl.length());
                textResult.setTitleURL(titleUrl);
                textResult.setGrade(AssessScore.assess(i++, "google", textResult.getTitle(), keyword));
                resultList.add(textResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
