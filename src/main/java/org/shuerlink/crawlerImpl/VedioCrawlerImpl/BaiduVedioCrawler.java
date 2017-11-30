package org.shuerlink.crawlerImpl.VedioCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.model.VedioResult;
import org.shuerlink.util.AssessScore;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

public class BaiduVedioCrawler extends VedioCrawler {
    private static final String url = "http://v.baidu.com/v?";

    private Site site = Site.me().setSleepTime(0).setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    public static BaiduVedioCrawler newInstance(String keyword, int start) {
        return new BaiduVedioCrawler(keyword, start);
    }

    private BaiduVedioCrawler(String keyword, int start) {
        setStart(String.valueOf(start)).setKeyword(keyword);
    }

    @Override
    public void process(Page page) {
        process(page.getHtml().getDocument());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public LinkedList<VedioResult> process(Document document) {
        LinkedList<VedioResult> resultLinkedList = new LinkedList<>();
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
            resultLinkedList.add(vedioResult);
        }
        setVedioResults(resultLinkedList);
        return resultLinkedList;
    }

    @Override
    public String getUrl() {
        return url + "pn=" + getStart() + "&ie=utf-8&word=" + getKeyword();
    }
}
