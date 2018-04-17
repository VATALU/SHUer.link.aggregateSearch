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
public class BingVideoCallablePageProcessor extends VideoCallablePageProcessor {
    private static final String url = "http://cn.bing.com/videos/search?q=";

    @Override

    public LinkedList<VideoResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<VideoResult> process(Document document) {
        LinkedList<VideoResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("div.dg_u");
        int i = 1;
        for (Element element : elements) {
            VideoResult videoResult = new VideoResult();
            //设置搜索引擎
            videoResult.setSearchEngine("必应");
            //设置score
            videoResult.setScore(AssessScore.assessBySearchEngine(i++, "bing"));
            //设置time
            String time = element.select("div.mc_bc items").text();
            videoResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("div.rms_iac").attr("data-src");
            videoResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a.mc_vtvc_link").attr("href");
            videoResult.setUrl(url);
            //设置title
            String title = element.select("div.mc_vtvc_title").attr("title");
            //title = title.substring(0, title.indexOf("来"));
            videoResult.setTitle(title);
            //设置publisher
            String publisher = element.select("div.mc_vtvc_meta_row.mc_vtvc_meta_row_channel").text();
            videoResult.setPublisher(publisher);
            //设置publisherTime
            String publisherTime = element.select("div.mc_vtvc_meta_row").text();
            videoResult.setPublishTime(publisherTime);
            resultLinkedList.add(videoResult);
        }
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword + "&qs=n&form=QBVR&sp=-1&first" + start;
    }
}