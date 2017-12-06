package org.shuerlink.crawlerImpl.VedioCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.VedioCallablePageProcessor;
import org.shuerlink.model.Result.VedioResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

import java.util.LinkedList;

@Repository
public class BilibiliVedioCallablePageProcessor extends VedioCallablePageProcessor {
    private static final String url = "https://search.bilibili.com/all?keyword=";

    @Override

    public LinkedList<VedioResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<VedioResult> process(Document document) {
        LinkedList<VedioResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("li.video.matrix");
        int i = 1;
        for (Element element : elements) {
            VedioResult vedioResult = new VedioResult();
            //设置搜索引擎
            vedioResult.setSearchEngine("bilibili");
            //设置score
            vedioResult.setScore(AssessScore.assess(i++, "bilibili"));
            //设置time
            String time = element.select("span.so-imgTag_rb").text();
            vedioResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("div.img").select("img").attr("data-src").substring(2);
            vedioResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a").attr("href").substring(2);
            vedioResult.setUrl(url);
            //设置title
            String title = element.select("a").attr("title");
            vedioResult.setTitle(title);
            //设置publisher
            String publisher = element.select("a.up-name").text();
            vedioResult.setPublisher(publisher);
            //设置publisherTime
            String publisherTime = element.select("span.so-icon.time").text();
            vedioResult.setPublishTime(publisherTime);
            resultLinkedList.add(vedioResult);
        }
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword + "&page=" + (start / 10 + 1);
    }
}
