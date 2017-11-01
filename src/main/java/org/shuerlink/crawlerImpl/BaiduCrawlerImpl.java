package org.shuerlink.crawlerImpl;

import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.MusicCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.MusicResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

@Repository
public class BaiduCrawlerImpl implements WebPageCrawler, MusicCrawler, ImageCrawler, VedioCrawler {
    private static String baidu = "http://www.baidu.com/s?wd=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<WebPageResult>();
            Document doc = Jsoup.connect(baidu + keyword).userAgent("Mozilla").timeout(3000).get();

            //result-op.c-container.xpath-log
            Elements result_op_c_container_xpath_log = doc.select("div.result-op.c-container.xpath-log");
            for (Element result : result_op_c_container_xpath_log) {
                WebPageResult webPageResult = new WebPageResult();
                webPageResult.setSearchEngine("百度搜索");
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                Elements baike_abstract = result.select("div.c-span18.c-span-last");
                if (!baike_abstract.html().equals("")) {
                    Element p = baike_abstract.select("p").first();
                    webPageResult.setTitle(p.text());
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu", webPageResult.getTitle(), webPageResult.getUrl()));
                    resultList.add(webPageResult);
                }

                Elements tieba_abstract = result.select("div.op-tieba-general-main-col.op-tieba-general-main-con");
                if (!tieba_abstract.html().equals("")) {
                    webPageResult.setTitle(
                            tieba_abstract.select("p").text());
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu", webPageResult.getTitle(), webPageResult.getUrl()));
                    resultList.add(webPageResult);
                }
            }
            //result-op.c-container

            //result.c-container
            Elements results_c_container = doc.select("div.result.c-container");
            for (Element result : results_c_container) {
                WebPageResult searchResult = new WebPageResult();
                searchResult.setSearchEngine("百度搜索");
                Elements title = result.select("h3");
                searchResult.setTitle(title.text());
                searchResult.setUrl(title.select("a[href]").attr("href"));
                searchResult.setTitle(result.select(".c-abstract").text());
                searchResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu", searchResult.getTitle(), searchResult.getUrl()));
                resultList.add(searchResult);
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

    @Override
    public LinkedList<MusicResult> getMusicResult(String keyword) {
        return null;
    }


}
