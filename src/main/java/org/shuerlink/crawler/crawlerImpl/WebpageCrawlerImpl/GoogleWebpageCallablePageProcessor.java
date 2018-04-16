package org.shuerlink.crawler.crawlerImpl.WebpageCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.WebPageCallablePageProcessor;
import org.shuerlink.model.Result.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

import java.util.LinkedList;

@Repository
public class GoogleWebpageCallablePageProcessor extends WebPageCallablePageProcessor {

    public static final String url = "https://g6.shuosc.org/search?";

    @Override
    public LinkedList<WebPageResult> getResults(Page page) {
        return   process(page.getHtml().getDocument());
    }

    public LinkedList<WebPageResult> process(Document document) {
        LinkedList<WebPageResult> resultList = new LinkedList<>();
        Elements results = document.select("div.g");
        int i = 1;
        for (Element result : results) {
            WebPageResult webPageResult = new WebPageResult();
            webPageResult.setSearchEngine("谷歌");
            Elements piece = result.select("h3");
            webPageResult.setTitle(piece.text());
            String titleUrl = piece.select("a[href]").attr("href");
            String discription = result.select("span.st").text();
            if (discription.equals("")) {
                continue;
            }
            webPageResult.setDescription(discription);
            titleUrl = titleUrl.substring(7, titleUrl.length());
            webPageResult.setUrl(titleUrl);
            webPageResult.setScore(AssessScore.assessBySearchEngine(i++, "google"));
            resultList.add(webPageResult);
        }
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "q=" + keyword + "&start" +start + "&num" +num + "&lr=lang_zh-CN";
    }

}
