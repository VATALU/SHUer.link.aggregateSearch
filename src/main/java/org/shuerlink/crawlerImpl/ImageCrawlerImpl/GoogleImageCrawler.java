package org.shuerlink.crawlerImpl.ImageCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.util.AssessScore;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleImageCrawler extends ImageCrawler {

    public static final String url = "http://g.shuer.link/search?";

    private Site site = Site.me().setSleepTime(0).setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    public static GoogleImageCrawler newInstance(String keyword) {
        return new GoogleImageCrawler(keyword);
    }

    private GoogleImageCrawler(String keyword) {
        setKeyword(keyword);
    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
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
                imageResult.setSearchEngine("谷歌搜索");
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
        setImageResults(resultList);
        return resultList;
    }

    @Override
    public String getUrl() {
        return url + "tbm=isch" + "&q=" + getKeyword();
    }

}