package org.shuerlink.crawler.crawlerImpl.WebpageCrawlerImpl;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.WebPageCallablePageProcessor;
import org.shuerlink.model.Result.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

@Repository
public class BingWebpageCallablePageProcessor extends WebPageCallablePageProcessor {
    private static final String url = "https://www.bing.com/search?";

    @Override
    public LinkedList<WebPageResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<WebPageResult> process(Document document) {
        LinkedList<WebPageResult> resultList = new LinkedList<>();
        Elements results = document.select(".b_algo");
        int i = 1;
        for (Element result : results) {
            Elements s = result.select("h2");
            WebPageResult webPageResult = new WebPageResult();
            webPageResult.setSearchEngine("必应");
            webPageResult.setTitle(s.text());
            webPageResult.setUrl(s.select("a[href]").attr("href"));
            webPageResult.setDescription(result.select("p").text());
            webPageResult.setScore(AssessScore.assess(i++, "bing"));
            resultList.add(webPageResult);
        }
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "q=" + keyword + "&first=" + start;
    }

}
