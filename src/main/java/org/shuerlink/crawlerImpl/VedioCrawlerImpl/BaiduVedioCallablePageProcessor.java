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
public class BaiduVedioCallablePageProcessor extends VedioCallablePageProcessor {
    private static final String url = "http://v.baidu.com/v?";

    @Override
    public LinkedList<VedioResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
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
        return resultLinkedList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "pn=" + start + "&ie=utf-8&word=" + keyword;
    }

}
