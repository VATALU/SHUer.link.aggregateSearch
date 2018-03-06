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
public class BilibiliVideoCallablePageProcessor extends VideoCallablePageProcessor {
    private static final String url = "https://search.bilibili.com/all?keyword=";

    @Override

    public LinkedList<VideoResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<VideoResult> process(Document document) {
        LinkedList<VideoResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("li.video.matrix");
        int i = 1;
        for (Element element : elements) {
            VideoResult videoResult = new VideoResult();
            //设置搜索引擎
            videoResult.setSearchEngine("bilibili");
            //设置score
            videoResult.setScore(AssessScore.assess(i++, "bilibili"));
            //设置time
            String time = element.select("span.so-imgTag_rb").text();
            videoResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("div.img").select("img").attr("data-src").substring(2);
            videoResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a").attr("href").substring(2);
            videoResult.setUrl(url);
            //设置title
            String title = element.select("a.title").html().replace(" class=\"keyword\"","");
            videoResult.setTitle(title);
            //设置publisher
            String publisher = element.select("a.up-name").text();
            videoResult.setPublisher(publisher);
            //设置publisherTime
            String publisherTime = element.select("span.so-icon.time").text();
            videoResult.setPublishTime(publisherTime);
            resultLinkedList.add(videoResult);
        }
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword + "&page=" + (start / 10 + 1);
    }
}
