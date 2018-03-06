package org.shuerlink.crawler.crawlerImpl.VideoCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.VideoCallablePageProcessor;
import org.shuerlink.model.Result.VideoResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

import java.util.LinkedList;

@Repository
public class BaiduVideoCallablePageProcessor extends VideoCallablePageProcessor {
    private static final String url = "http://v.baidu.com/v?";

    @Override
    public LinkedList<VideoResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<VideoResult> process(Document document) {
        LinkedList<VideoResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("li.result");
        int i = 1;
        for (Element element : elements) {
            VideoResult videoResult = new VideoResult();
            //设置搜索引擎
            videoResult.setSearchEngine("百度");
            //设置score
            videoResult.setScore(AssessScore.assess(i++,"baidu"));
            //设置time
            String time = element.select("span.info").text();
            videoResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("img.img-blur-layer").attr("src");
            videoResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a").attr("href");
            if(url.startsWith("/link")){
                url = "http://v.baidu.com"+url;
            }
            videoResult.setUrl(url);
            //设置title
            String title = element.select("span.title").html();
            videoResult.setTitle(title);
            //设置publisher
            String publisher = element.select("span.site").text();
            videoResult.setPublisher(publisher);
            //设置publisherTime
            String publisherTime=element.select("span.so-icon.time").text();
            videoResult.setPublishTime(publisherTime);
            resultLinkedList.add(videoResult);
        }
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "pn=" + start + "&ie=utf-8&word=" + keyword;
    }

}
