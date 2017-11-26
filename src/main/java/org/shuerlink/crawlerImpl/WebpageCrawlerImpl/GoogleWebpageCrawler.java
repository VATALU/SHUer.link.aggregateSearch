package org.shuerlink.crawlerImpl.WebpageCrawlerImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.logging.Logger;

@Repository
public class GoogleWebpageCrawler extends WebPageCrawler {

    public static final String GOOGLE = "https://g.shuer.link/search?";

    private Site site = Site.newInstance().setTimeOut(3000).setRetryTimes(3).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
            .setUrl(GOOGLE);
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public LinkedList<WebPageResult> process(Document document) {
        LinkedList<WebPageResult> resultList = new LinkedList<>();
        Elements results = document.select("div.g");
        int i = 1;
        for (Element result : results) {
            WebPageResult webPageResult = new WebPageResult();
            webPageResult.setSearchEngine("谷歌搜索");
            Elements piece = result.select("h3");
            webPageResult.setTitle(piece.text());
            String titleUrl = piece.select("a[href]").attr("href");
            String discription = result.select("span.st").text();
            if (discription.equals("")) {
                continue;
            }
            webPageResult.setDiscription(discription);
            titleUrl = titleUrl.substring(7, titleUrl.length());
            webPageResult.setUrl(titleUrl);
            webPageResult.setScore(AssessScore.assess(i++, "google"));
            resultList.add(webPageResult);
        }
        return resultList;
    }
}
