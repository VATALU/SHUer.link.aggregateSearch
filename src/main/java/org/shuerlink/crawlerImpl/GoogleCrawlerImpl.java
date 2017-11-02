package org.shuerlink.crawlerImpl;

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

import java.util.LinkedList;

@Repository
public class GoogleCrawlerImpl implements WebPageCrawler, VedioCrawler, ImageCrawler {
    public static final String google = "https://g.shuer.link/search?q=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<WebPageResult>();
            Document doc = Jsoup.connect(google + keyword + "&num=" + num + "&start=" + start + "&lr=lang_zh-CN").userAgent("Mozilla").timeout(4000).get();
            Elements results = doc.select("div.g");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start, int num) {
        return null;
    }

}
