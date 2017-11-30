package org.shuerlink.crawlerImpl.WebpageCrawlerImpl;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

public class BingWebpageCrawler extends WebPageCrawler {
    private static final String url = "https://www.bing.com/search?";

    private Site site = Site.me().setSleepTime(0).setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    private BingWebpageCrawler(String keyword, int start) {
        setKeyword(keyword).setStart(String.valueOf(start));
    }

    public static BingWebpageCrawler newInstance(String keyword, int start) {
        return new BingWebpageCrawler(keyword, start);
    }

    @Override
    public void process(Page page) {
        process(page.getHtml().getDocument());
    }

    @Override
    public Site getSite() {
        return site;
    }

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
        setWebPageResults(resultList);
        return resultList;
    }

    @Override
    public String getUrl() {
        return url + "q=" + getKeyword() + "&first=" + getStart();
    }
}
