package org.shuerlink.crawlerImpl.ImageCrawlerImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.util.AssessScore;

import java.net.URLEncoder;
import java.util.LinkedList;

public class BingImageCrawler extends ImageCrawler {
    private static final String IMAGE = "https://cn.bing.com/images/search?";

    private Site site = Site.newInstance().setTimeOut(3000).setRetryTimes(3).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
            .setUrl(IMAGE);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<>();
        Elements items = document.select(".item");
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
        return resultList;
    }
}
