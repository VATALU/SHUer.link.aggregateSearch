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
public class ZhihuShareCallablePageProcessor extends ShareCallablePageProcessor {
    private static final String url = "https://www.zhihu.com/search?type=content&q=";
    private static final String temp="www.zhihu.com";
    @Override
    public LinkedList<ShareResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<ShareResult> process(Document document) {
        LinkedList<ShareResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("div.Card").select("div.List-item");
        System.out.println(elements);
        int i = 1;
        for (Element element : elements) {
            ShareResult shareResult = new ShareResult();
            //设置搜索引擎
            shareResult.setSearchEngine("知乎");
            //设置score
            shareResult.setScore(AssessScore.assessBySearchEngine(i++,"zhihu"));
            //设置authorName
            String authorName = element.select("span.RichText.CopyrightRichText-richText").text();
            if (authorName.length()==0)
                authorName="匿名用户";
            else authorName=authorName.substring(0,authorName.indexOf("："));
            shareResult.setAuthorname(authorName);
            //设置imageurl
            shareResult.setImageUrl(null);
            //设置url
            String url = element.select("meta").select("[itemprop=\"url\"]").attr("content");
            if (url.length()==0)
                continue;
            shareResult.setUrl(url);
            //设置title
            String title = element.select("span.Highlight").text();
            shareResult.setTitle(title);
            //设置authorurl
            shareResult.setAuthorurl(null);
            //设置time
            shareResult.setTime(null);
            //设置voters
            String ss=getInt(element.select("button.Button.VoteButton.VoteButton--up").text());
            int voters;
            if (ss.length()==0)
                voters=0;
            else
                voters=Integer.parseInt(ss);
            shareResult.setVoters(voters);
            //设置comment
            String s=element.select("button.Button.ContentItem-action.Button--plain.Button--withIcon.Button--withLabel").text().trim();
            int comment;
            if (s.indexOf("条")>0){
                comment=Integer.parseInt(s.substring(2,s.indexOf("条")-1));
            }
            else
                comment=0;
            shareResult.setComment(comment);
            //设置discription
            String discription=element.select("span.RichText.CopyrightRichText-richText").text();
            discription=discription.substring(discription.indexOf("：")+1);
            shareResult.setDescription(discription);
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
