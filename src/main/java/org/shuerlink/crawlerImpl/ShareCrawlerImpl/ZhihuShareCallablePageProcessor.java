package org.shuerlink.crawlerImpl.ShareCrawlerImpl;

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
public class ZhihuShareCallablePageProcessor extends ShareCallablePageProcessor {
    private static final String url = "https://www.zhihu.com/search?type=content&q=";
    private static final String temp="www.zhihu.com";
    @Override
    public LinkedList<ShareResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<ShareResult> process(Document document) {
        LinkedList<ShareResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("li.item.clearfix").select("[data-type=\"Answer\"]");
        System.out.println(elements);
        int i = 1;
        for (Element element : elements) {
            ShareResult shareResult = new ShareResult();
            //设置搜索引擎
            shareResult.setSearchEngine("知乎");
            //设置score
            shareResult.setScore(AssessScore.assess(i++,"zhihu"));
            //设置authorName
            String authorName = element.select("span.author-link-line").select("a").text();
            if (authorName.length()==0)
                authorName="匿名用户";
            shareResult.setAuthorname(authorName);
            //设置imageurl
            String imageurl = element.select("img.Avatar.Avatar--xs").attr("src");
            shareResult.setImageUrl(imageurl);
            //设置url
            String url = element.select("a.js-title-link").attr("href");
            shareResult.setUrl(temp+url);
            //设置title
            String title = element.select("a.js-title-link").text();
            shareResult.setTitle(title);
            //设置authorurl
            String authorurl = element.select("a.author.author-link").attr("href");
            if (authorurl.length()==0)
                authorurl=null;
            else
                authorurl=temp+authorurl;
            shareResult.setAuthorurl(authorurl);
            //设置time
            String time=element.select("a.time.text-muted").text();
            shareResult.setTime(time);
            //设置voters
            String ss=getInt(element.select("a.zm-item-vote-count.hidden-expanded.js-expand.js-vote-count").text());
            int voters;
            if (ss.length()==0){
                ss=getInt(element.select("div.zm-item-vote-count.js-expand.js-vote-count").text());
                if (ss.length()==0)
                    voters=0;
                else
                    voters=Integer.parseInt(ss);
            }
            else
                voters=Integer.parseInt(element.select("a.zm-item-vote-count.hidden-expanded.js-expand.js-vote-count").text());
            shareResult.setVoters(voters);
            //设置comment
            String s=element.select("a.action-item.js-toggleCommentBox").select("span.label").text();
            int comment;
            if (s.indexOf("条")>0)
                comment=Integer.parseInt(s.substring(0,s.indexOf("条")-1));
            else
                comment=0;
            shareResult.setComment(comment);
            //设置discription
            String discription=element.select("div.summary.hidden-expanded").text();
            shareResult.setDiscription(discription);
            resultLinkedList.add(shareResult);
        }
        return resultLinkedList;
    }
    private String getInt(String str){
        str=str.trim();
        int i;
        for (i=0;i<str.length();i++){
            if (!Character.isDigit(str.charAt(i))){
                break;
            }
        }
        return str.substring(0,i);
    }
    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword;
    }
}
