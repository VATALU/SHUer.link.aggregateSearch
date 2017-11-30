package org.shuerlink.crawlerImpl.ImageCrawlerImpl;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.util.AssessScore;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BingImageCrawler extends ImageCrawler {
    private static final String url = "https://cn.bing.com/images/search?";

    private Site site = Site.me().setSleepTime(0).setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    public static BingImageCrawler newInstance(String keyword, int start) {
        return new BingImageCrawler(keyword, start);
    }

    private BingImageCrawler(String keyword, int start) {
        setKeyword(keyword).setStart(String.valueOf(start));
    }

    @Override
    public void process(Page page) {
        String html = page.getHtml().toString();
        int start = html.indexOf("<div id=\"mmComponent_images_1\"");
        int end = html.indexOf("<!--for counter factual flight view model logging-->");
        html = html.substring(start,end);
        process(new Html(html).getDocument());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<>();
        Elements uls = document.select("ul[data-row]");
        for (Element ul : uls) {
            Elements lis = uls.select("li[data-idx]");
            for(Element li : lis){
                ImageResult imageResult = new ImageResult();
                //设置搜索引擎
                imageResult.setSearchEngine("必应搜索");
                //设置url
                String urlInfo = li.select("a[m]").attr("m");
                Map<String, Object> map = new HashMap<String, Object>();

                JSONObject urlObject = JSONObject.parseObject(urlInfo);
                imageResult.setUrl(urlObject.get("murl").toString());
                //设置hostUrl
                imageResult.setHostUrl(urlObject.get("purl").toString());
                //设置width height type
                String fileInfos = li.select(".b_dataList").select("li").text();
                fileInfos = fileInfos.replace("<li>"," ").replace("</li>"," ");
                String[] fileInfo = fileInfos.split(" ");
                imageResult.setWidth(Integer.parseInt(fileInfo[0]));
                imageResult.setHeight(Integer.parseInt(fileInfo[2]));
                imageResult.setType(fileInfo[4]);
                //设置title
                imageResult.setTitle(fileInfo[5]);
                //设置discription
                //设置score
                imageResult.setScore(AssessScore.assess(Integer.parseInt(li.attr("data-idx")), "bing"));
                resultList.add(imageResult);
            }
        }
        setImageResults(resultList);
        return resultList;
    }

    @Override
    public String getUrl() {
        return url + "q=" + getKeyword() + "&qs=n&form=QBIR&first=" + getStart();
    }
}
