package org.shuerlink.crawlerImpl.ImageCrawlerImpl;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.model.BaiduImageResult;
import org.shuerlink.model.ImageResult;
import org.shuerlink.util.AssessScore;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

public class BaiduImageCrawler extends ImageCrawler {
    private static String IMAGE = "http://image.baidu.com/search/index?";
    private Site site = Site.newInstance().setTimeOut(3000).setRetryTimes(3).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
            .setUrl(IMAGE);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<ImageResult>();
        String html = document.html();
        int begin = html.lastIndexOf("'imgData'") + 10;
        int end = html.lastIndexOf("'fcadData'") - 20;
        String subHtml = html.substring(begin, end);
        String text = subHtml.substring(subHtml.indexOf("data") + 6, subHtml.length() - 1);

        //用fastjson将String转换json
        List<BaiduImageResult> baiduImageResults = JSON.parseArray(text, BaiduImageResult.class);
        //去除最后一项多余项
        baiduImageResults.remove(baiduImageResults.size() - 1);
        int i = 0;
        for (BaiduImageResult baiduImageResult : baiduImageResults) {
            ImageResult imageResult = new ImageResult();
            imageResult.setScore(AssessScore.assess(i++, "baidu"));
            imageResult.setTitle(baiduImageResult.getFromURLHost());
            imageResult.setDiscription(baiduImageResult.getFromPageTitle().replace("<strong>", "").replace("</strong>", ""));
            imageResult.setHostUrl(baiduImageResult.getFromURLHost());
            imageResult.setHeight(baiduImageResult.getHeight());
            imageResult.setWidth(baiduImageResult.getWidth());
            imageResult.setType(baiduImageResult.getType());
            imageResult.setUrl(baiduImageResult.getObjURL());
            imageResult.setSearchEngine("百度搜索");
            resultList.add(imageResult);
        }
        return resultList;
    }
}
