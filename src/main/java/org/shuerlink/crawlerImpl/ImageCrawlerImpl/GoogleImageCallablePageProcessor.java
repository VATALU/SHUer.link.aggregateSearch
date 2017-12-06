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

    public static final String url = "http://g.shuer.link/search?";

    @Override
    public LinkedList<ImageResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<>();
        Elements images_table = document.select(".images_table");
        Elements trs = images_table.select("tbody tr");
        int i = 0;
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            for (Element td : tds) {
                ImageResult imageResult = new ImageResult();
                //设置搜索引擎
                imageResult.setSearchEngine("谷歌");
                //设置url
                imageResult.setUrl(td.select("img").attr("src"));
                //设置hostUrl
                imageResult.setHostUrl(td.select("a").attr("href").substring(7));
                //设置title
                imageResult.setTitle(td.select("cite").attr("title"));
                //设置discription
                String html = td.html();
                int titleBegin = html.lastIndexOf("</cite><br>") + 11;
                int tileEnd = html.lastIndexOf("<br>");
                imageResult.setDiscription(html.substring(titleBegin, tileEnd).replace("</b>", " ").replace("<b>", " "));
                //设置height width
                Pattern p1 = Pattern.compile("\\d{1,}\\s×\\s\\d{1,}");
                Matcher m1 = p1.matcher(html);
                if (m1.find()) {
                    String[] size = m1.group().toString().split(" ");
                    imageResult.setWidth(Integer.parseInt(size[0]));
                    imageResult.setHeight(Integer.parseInt(size[2]));
                }
                //设置type
                int typeBegin = html.lastIndexOf("&nbsp;-&nbsp;") + 13;
                String type = html.substring(typeBegin, html.length());
                imageResult.setType(type);
                //设置score
                imageResult.setScore(AssessScore.assess(i++, "google"));
                resultList.add(imageResult);
            }
        }
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "tbm=isch" + "&q=" + keyword;
    }

}