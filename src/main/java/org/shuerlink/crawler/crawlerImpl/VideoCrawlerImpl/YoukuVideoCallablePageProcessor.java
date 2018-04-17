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
public class YoukuVideoCallablePageProcessor extends VideoCallablePageProcessor {
    private static final String url = "http://www.soku.com/search_video/q_";

    @Override

    public LinkedList<VideoResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<VideoResult> process(Document document) {
        LinkedList<VideoResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("div.v");
        int i = 1;
        for (Element element : elements) {
            VideoResult videoResult = new VideoResult();
            //设置搜索引擎
            videoResult.setSearchEngine("优酷土豆");
            //设置score
            videoResult.setScore(AssessScore.assessBySearchEngine(i++, "youku"));
            //设置time
            String time = element.select("span.v-time").text();
            videoResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("img").attr("src").substring(2);
            videoResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("div.v-link").select("a").attr("href").substring(2);
            videoResult.setUrl(url);
            //设置title
            String title = element.select("div.v-meta-title").select("a").text();
            videoResult.setTitle(title);
            //设置publisher
            String publisher = element.select("span.username").select("a").text();
            videoResult.setPublisher(publisher);
            resultLinkedList.add(videoResult);
            //设置publisherTime
            String publisherTime = element.select("span.r").text();
            videoResult.setPublishTime(publisherTime);
        }
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword;
    }
}