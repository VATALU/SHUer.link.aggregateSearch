package org.shuerlink.crawlerImpl;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import com.sleepycat.je.tree.IN;
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
    private static final String BING = "https://www.bing.com/search?q=";
    private static final String IMAGE = "https://cn.bing.com/images/search?qs=n&form=QBIR&q=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<>();
            keyword = URLEncoder.encode(keyword, "UTF-8");
            Document doc = Jsoup.connect(BING + keyword + "&first=" + start).userAgent("Mozilla").timeout(3000).get();
            Elements results = doc.select(".b_algo");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start) {
        LinkedList<ImageResult> resultList = null;
        try {
            resultList = new LinkedList<>();
            keyword = URLEncoder.encode(keyword, "UTF-8");
            Document doc = Jsoup.connect(IMAGE + keyword + "&first=" + start).userAgent("Mozilla").get();
            Elements items = doc.select(".item");
            int i = 0;
            for (Element item : items) {
                ImageResult imageResult = new ImageResult();
                //设置搜索引擎
                imageResult.setSearchEngine("必应搜索");
                //设置url
                imageResult.setUrl(item.select(".thumb").attr("href"));
                //设置width height type
                String fileInfos = item.select(".fileInfo").text();
                String[] fileInfo = fileInfos.split(" ");
                imageResult.setWidth(Integer.parseInt(fileInfo[0]));
                imageResult.setHeight(Integer.parseInt(fileInfo[2]));
                imageResult.setType(fileInfo[3]);
                //设置title
                imageResult.setTitle(item.select(".tit").text());
                //设置hostUrl
                imageResult.setHostUrl(item.select(".tit").attr("href"));
                //设置discription
                imageResult.setDiscription(item.select(".des").text());
                //设置score
                imageResult.setScore(AssessScore.assess(i++, "bing"));
                resultList.add(imageResult);
            }
        } catch (Exception e) {

        }
        return resultList;

    }

    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

}
