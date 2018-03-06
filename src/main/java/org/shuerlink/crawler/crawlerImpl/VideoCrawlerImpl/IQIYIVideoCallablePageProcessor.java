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
public class IQIYIVideoCallablePageProcessor extends VideoCallablePageProcessor {
    private static final String url = "http://so.iqiyi.com/so/q_";

    @Override

    public LinkedList<VideoResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<VideoResult> process(Document document) {
        LinkedList<VideoResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("li.list_item");
        int i = 1;
        for (Element element : elements) {
            VideoResult videoResult = new VideoResult();
            //设置搜索引擎
            videoResult.setSearchEngine("爱奇艺");
            //设置score
            videoResult.setScore(AssessScore.assess(i++, "IQIYI"));
            //设置time
            String time = element.select("span.icon-vInfo").text();
            videoResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("img").attr("src");
            videoResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a").attr("href");
            videoResult.setUrl(url);
            //设置title
            String title = element.select("h3.result_title").select("a").html();
            videoResult.setTitle(title);
            //设置publisher
            String publisher = element.select("a.result_info_link").text();
            if (publisher.length() == 0)
                publisher = element.select("em.result_info_desc").text().substring(11);
            videoResult.setPublisher(publisher);
            //设置publisherTime
            String publisherTime = element.select("em.result_info_desc").text().substring(0, 10);
            videoResult.setPublishTime(publisherTime);
            resultLinkedList.add(videoResult);
        }
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword + "_ctg__t_0_page_" + (start / 10 + 1) + "_p_1_qc_1_rd__site__m_1_bitrate_?source=correct";
    }
}