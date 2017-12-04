package org.shuerlink.crawlerImpl.ShareCrawlerImpl;

import org.shuerlink.crawler.ShareCallablePageProcessor;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.*;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import static ucar.coord.Coordinate.Type.time;

@Repository
public class JianshuShareCallablePageProcessor extends ShareCallablePageProcessor {
    private static final String url = "http://www.jianshu.com/search?q=";
    private static final String temp="http://www.jianshu.com";
    @Override
    public LinkedList<ShareResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<ShareResult> process(Document document) {
        LinkedList<ShareResult> resultLinkedList = new LinkedList<>();
        System.out.println("ccg "+document);
        Elements elements = document.select("ul.note-list").select("li");
        System.out.println("gxy "+elements);
        int i = 1;
        for (Element element : elements) {
            ShareResult shareResult = new ShareResult();
            //设置搜索引擎
            shareResult.setSearchEngine("简书搜索");
            //设置score
            shareResult.setScore(AssessScore.assess(i++,"jianshu"));
            //设置authorName
            String authorName = element.select("a.nickname").text();
            shareResult.setAuthorname(authorName);
            //设置imageurl
            String imageurl = element.select("a.avatar").select("img").attr("src");
            shareResult.setImageurl(imageurl);
            //设置url
            String url = element.select("a.title").attr("href");
            shareResult.setUrl(temp+url);
            //设置title
            String title = element.select("a.title").text();
            shareResult.setTitle(title);
            //设置authorurl
            String authorurl = element.select("a.nickname").attr("href");
            shareResult.setAuthorurl(temp+authorurl);
            //设置time
            String time=element.select("span.time").text();
            shareResult.setTime(time);
            //设置voters
            String ss=element.select("div.meta").text();
            int voters;
            if (ss.length()==0)
                voters=0;
            else
                voters=Integer.parseInt(ss);
            shareResult.setVoters(voters);
            //设置visitor
            //String sss=element.select("div.meta").select("a").first().text();
            int visitor=Integer.parseInt("999");
            shareResult.setVisitor(visitor);
            //设置comment
            //String s=element.select("div.meta").select("a").last().text();
            String s="";
            int comment;
            if (s.length()==0)
                comment=0;
            else
                comment=Integer.parseInt(getInt(s));
            shareResult.setComment(comment);
            //设置discription
            String discription=element.select("p.abstract").text();
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