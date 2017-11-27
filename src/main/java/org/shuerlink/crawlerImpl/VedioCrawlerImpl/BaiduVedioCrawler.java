package org.shuerlink.crawlerImpl.VedioCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.model.VedioResult;
import org.shuerlink.util.AssessScore;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

public class BaiduVedioCrawler extends VedioCrawler {
    private static final String url = "http://v.baidu.com/v?";

    private Site site = Site.newInstance().setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    public static BaiduVedioCrawler newInstance(String keyword, int start) {
        return new BaiduVedioCrawler(keyword, start);
    }

    private BaiduVedioCrawler(String keyword, int start) {
        try {
            keyword = URLEncoder.encode(keyword,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setStart(String.valueOf(start)).setKeyword(keyword);
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public LinkedList<VedioResult> process(Document document) {
        LinkedList<VedioResult> vedioResults = new LinkedList<>();
        Elements elements = document.select("li.result");
        int i = 1;
        for (Element element : elements) {
            VedioResult vedioResult = new VedioResult();
            //设置搜索引擎
            vedioResult.setSearchEngine("百度搜索");
            //设置score
            vedioResult.setScore(AssessScore.assess(i++,"baidu"));
            //设置time
            String time = element.select("span.info").text();
            vedioResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("img.img-blur-layer").attr("src");
            vedioResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a").attr("href");
            if(url.startsWith("/link")){
                url = "http://v.baidu.com"+url;
            }
            vedioResult.setUrl(url);
            //设置title
            String title = element.select("a").attr("title");
            vedioResult.setTitle(title);
            //设置publisher
            String publisher = element.select("span.site").text();
            vedioResult.setPublisher(publisher);
            vedioResults.add(vedioResult);
        }
        return vedioResults;
    }

    @Override
    public String getUrl() {
        return url + "pn=" + getStart() + "&ie=utf-8&word=" + getKeyword();
    }
}
