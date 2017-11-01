package org.shuerlink.crawlerImpl;

import java.net.URLEncoder;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

@Repository
public class BingCrawlerImpl implements WebPageCrawler, ImageCrawler, VedioCrawler {
    private static String bing = "https://www.bing.com/search?q=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<WebPageResult>();
            keyword = URLEncoder.encode(keyword, "UTF-8");
            Document doc = Jsoup.connect(bing + keyword).userAgent("Mozilla").timeout(3000).get();
            Elements results = doc.select(".b_algo");
            int i = 1;
            for (Element result : results) {
                Elements s = result.select("h2");
                WebPageResult webPageResult = new WebPageResult();
                webPageResult.setSearchEngine("必应搜索");
                webPageResult.setTitle(s.text());
                webPageResult.setUrl(s.select("a[href]").attr("href"));
                webPageResult.setTitle(result.select("p").text());
                webPageResult.setScore(AssessScore.assess(i++, "bing", webPageResult.getTitle(), keyword));
                resultList.add(webPageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword) {
        return null;
    }

    @Override
    public LinkedList<ImageResult> getImageResult(String keyword) {
        return null;
    }

}
