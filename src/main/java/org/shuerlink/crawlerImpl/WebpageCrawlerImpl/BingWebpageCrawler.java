package org.shuerlink.crawlerImpl.WebpageCrawlerImpl;

import java.net.URLEncoder;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

@Repository
public class BingWebpageCrawler extends WebPageCrawler {
    private static final String BING = "https://www.bing.com/search?";

    private Site site =Site.newInstance().setTimeOut(3000).setRetryTimes(3).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
            .setUrl(BING);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public LinkedList<WebPageResult> process(Document document) {
        LinkedList<WebPageResult> resultList = new LinkedList<>();
        Elements results = document.select(".b_algo");
        int i = 1;
        for (Element result : results) {
            Elements s = result.select("h2");
            WebPageResult webPageResult = new WebPageResult();
            webPageResult.setSearchEngine("必应搜索");
            webPageResult.setTitle(s.text());
            webPageResult.setUrl(s.select("a[href]").attr("href"));
            webPageResult.setDiscription(result.select("p").text());
            webPageResult.setScore(AssessScore.assess(i++, "bing"));
            resultList.add(webPageResult);
        }
        return resultList;
    }
}
