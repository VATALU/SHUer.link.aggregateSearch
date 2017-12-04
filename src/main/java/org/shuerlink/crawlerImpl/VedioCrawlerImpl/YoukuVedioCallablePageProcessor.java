package org.shuerlink.crawlerImpl.VedioCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.VedioCallablePageProcessor;
import org.shuerlink.model.VedioResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.LinkedList;
import java.util.List;

@Repository
public class YoukuVedioCallablePageProcessor extends VedioCallablePageProcessor {
    private static final String url = "http://www.soku.com/search_video/q_";
    @Override

    public LinkedList<VedioResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<VedioResult> process(Document document) {
        LinkedList<VedioResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("div.v");
        int i = 1;
        for (Element element : elements) {
            VedioResult vedioResult = new VedioResult();
            //设置搜索引擎
            vedioResult.setSearchEngine("优酷搜索");
            //设置score
            vedioResult.setScore(AssessScore.assess(i++,"youku"));
            //设置time
            String time = element.select("span.v-time").text();
            vedioResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("img").attr("src").substring(2);
            vedioResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("div.v-link").select("a").attr("href").substring(2);
            vedioResult.setUrl(url);
            //设置title
            String title = element.select("div.v-link").select("a").attr("title");
            vedioResult.setTitle(title);
            //设置publisher
            String publisher = element.select("span.username").select("a").text();
            vedioResult.setPublisher(publisher);
            resultLinkedList.add(vedioResult);
            //设置publisherTime
            String publisherTime=element.select("span.r").text();
            vedioResult.setPublishTime(publisherTime);
        }
        return resultLinkedList;
    }
    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword;
    }
}