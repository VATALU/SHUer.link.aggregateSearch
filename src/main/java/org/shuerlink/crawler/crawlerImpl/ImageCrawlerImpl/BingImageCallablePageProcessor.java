package org.shuerlink.crawler.crawlerImpl.ImageCrawlerImpl;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCallablePageProcessor;
import org.shuerlink.model.Result.ImageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Repository
public class BingImageCallablePageProcessor extends ImageCallablePageProcessor {
    private static final String url = "https://cn.bing.com/images/search?";

    @Override
    public LinkedList<ImageResult> getResults(Page page) {
        String html = page.getHtml().toString();
        int start = html.indexOf("<div id=\"mmComponent_images_1\"");
        int end = html.indexOf("<!--for counter factual flight view model logging-->");
        html = html.substring(start,end);
        return process(new Html(html).getDocument());
    }

    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<>();
        Elements uls = document.select("ul[data-row]");
        for (Element ul : uls) {
            Elements lis = uls.select("li[data-idx]");
            for(Element li : lis){
                ImageResult imageResult = new ImageResult();
                //设置搜索引擎
                imageResult.setSearchEngine("必应");
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
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "q=" + keyword + "&qs=n&form=QBIR&first=" + start;
    }

}
