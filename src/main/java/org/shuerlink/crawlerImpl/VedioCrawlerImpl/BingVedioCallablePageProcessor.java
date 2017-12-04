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
public class BingVedioCallablePageProcessor extends VedioCallablePageProcessor {
    private static final String url = "http://cn.bing.com/videos/search?q=";
    @Override

    public LinkedList<VedioResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<VedioResult> process(Document document) {
        LinkedList<VedioResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("div.dg_u");
        int i = 1;
        for (Element element : elements) {
            VedioResult vedioResult = new VedioResult();
            //设置搜索引擎
            vedioResult.setSearchEngine("Bing搜索");
            //设置score
            vedioResult.setScore(AssessScore.assess(i++,"bing"));
            //设置time
            String time = element.select("div.vrhdata").attr("du");
            vedioResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("div.vthumb").select("img").attr("src");
            vedioResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a.dv_i").attr("href");
            vedioResult.setUrl(url);
            //设置title
            String title = element.select("div.vthumb").attr("alt");
            vedioResult.setTitle(title);
            //设置publisher
            String publisher = "By searching\n";
            vedioResult.setPublisher(publisher);
            resultLinkedList.add(vedioResult);
        }
        return resultLinkedList;
    }
    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword;
    }
}