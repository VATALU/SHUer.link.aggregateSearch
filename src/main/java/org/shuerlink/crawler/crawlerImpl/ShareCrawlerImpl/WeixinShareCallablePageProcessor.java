package org.shuerlink.crawler.crawlerImpl.ShareCrawlerImpl;

import org.shuerlink.crawler.ShareCallablePageProcessor;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.Result.ShareResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

@Repository
public class WeixinShareCallablePageProcessor extends ShareCallablePageProcessor {
    private static final String url = "http://weixin.sogou.com/weixin?type=2&query=";
    @Override
    public LinkedList<ShareResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<ShareResult> process(Document document) {
        LinkedList<ShareResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("ul.news-list").select("li");
        int i = 1;
        for (Element element : elements) {
            ShareResult shareResult = new ShareResult();
            //设置搜索引擎
            shareResult.setSearchEngine("微信");
            //设置score
            shareResult.setScore(AssessScore.assess(i++,"weixin"));
            //设置authorName
            String authorName = element.select("div.s-p").select("a.account").text();
            shareResult.setAuthorname(authorName);
            //设置imageurl
            String imageurl = element.select("div.img-box").select("img").attr("src");
            shareResult.setImageUrl(imageurl);
            //设置url
            String url = element.select("div.txt-box").select("a").attr("data-share");
            shareResult.setUrl(url);
            //设置title
            String title = element.select("div.txt-box").select("a").first().html().replace("<!--red_beg-->","").replace("<!--red_end-->","");
            shareResult.setTitle(title);
            //设置authorurl
            String authorurl = element.select("div.s-p").select("a.account").attr("href");
            shareResult.setAuthorurl(authorurl);
            //设置time
            String time=element.select("div.s-p").select("span.s2").text();
            shareResult.setTime(time);
            //设置discription
            String discription=element.select("p.txt-info").text();
            shareResult.setDescription(discription);
            resultLinkedList.add(shareResult);
        }
        return resultLinkedList;
    }
    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword;
    }
}
