package org.shuerlink.crawlerImpl.ImageCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCallablePageProcessor;
import org.shuerlink.model.Result.ImageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class GoogleImageCallablePageProcessor extends ImageCallablePageProcessor {

    public static final String url = "https://g6.shuosc.org/search?";

    @Override
    public LinkedList<ImageResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<>();
        Elements trs = document.select("div.rg_bx.rg_di.rg_el.ivg-i");
        //System.out.println(trs);
        int i = 0;
        for (Element tr : trs) {
                ImageResult imageResult = new ImageResult();
                //设置搜索引擎
                imageResult.setSearchEngine("谷歌");
                //设置url
                String url=tr.select("div.rg_meta.notranslate").text();
                url=url.substring(url.indexOf("\"ou\":\"")+6,url.indexOf("\",\"ow\""));
                imageResult.setUrl(url);
                //设置hostUrl
                String hostUrl=tr.select("div.rg_meta.notranslate").text();
                hostUrl=hostUrl.substring(hostUrl.indexOf("\"ru\":\"")+6,hostUrl.indexOf("\",\"s\""));
                imageResult.setHostUrl(hostUrl);
                //设置title
                String title=tr.select("div.rg_meta.notranslate").text();
                if (title.indexOf("\",\"st\"")>0)
                    title=title.substring(title.indexOf("\"s\":\"")+5,title.indexOf("\",\"st\""));
                else
                    title=null;
                imageResult.setTitle(title);
                //设置discription
                String discription=tr.select("div.rg_meta.notranslate").text();
                discription=discription.substring(discription.indexOf("\"pt\":\"")+6,discription.indexOf("\",\"rid\""));
                imageResult.setDiscription(discription);
                //设置height width
                String height=tr.select("span.rg_ilmn").text().trim();
                System.out.println("gxy "+height);
                height=height.substring(height.indexOf(" × ")+3,height.indexOf(" - "));
                imageResult.setHeight(Integer.parseInt(height));
                String width=tr.select("span.rg_ilmn").text().trim();
                width=width.substring(0,width.indexOf(" × "));
                imageResult.setWidth(Integer.parseInt(width));
                //设置type
                String type=tr.select("span.rg_ilmn").text().trim();
                type=type.substring(type.indexOf(" - ")+3);
                imageResult.setType(type);
                //设置score
                imageResult.setScore(AssessScore.assess(i++, "google"));
                resultList.add(imageResult);

        }
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "tbm=isch" + "&q=" + keyword;
    }

}