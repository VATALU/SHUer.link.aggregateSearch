package org.shuerlink.crawlerImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class GoogleCrawlerImpl implements WebPageCrawler, VedioCrawler, ImageCrawler {
    private static Logger logger = Logger.getLogger(GoogleCrawlerImpl.class.getName());

    public static final String google = "https://g.shuer.link/search?q=";
    public static final String image = "http://g.shuer.link/search?tbm=isch&q=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            resultList = new LinkedList<WebPageResult>();
            Document doc = Jsoup.connect(google + keyword + "&num=" + num + "&start=" + start + "&lr=lang_zh-CN").userAgent("Mozilla").timeout(4000).get();
            Elements results = doc.select("div.g");
            int i = 1;
            for (Element result : results) {
                WebPageResult webPageResult = new WebPageResult();
                webPageResult.setSearchEngine("谷歌搜索");
                Elements piece = result.select("h3");
                webPageResult.setTitle(piece.text());
                String titleUrl = piece.select("a[href]").attr("href");
                String discription = result.select("span.st").text();
                if (discription.equals("")) {
                    continue;
                }
                webPageResult.setDiscription(discription);
                titleUrl = titleUrl.substring(7, titleUrl.length());
                webPageResult.setUrl(titleUrl);
                webPageResult.setScore(AssessScore.assess(i++, "google"));
                resultList.add(webPageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("谷歌搜索WebPWebPage搜索失败");
        }
        return resultList;
    }

    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start, int num) {
        LinkedList<ImageResult> resultList = null;
        try {
            keyword = URLEncoder.encode(keyword,"UTF-8");
            resultList = new LinkedList<ImageResult>();
            Document doc= Jsoup.connect(image+keyword).userAgent("Mozilla").get();
            Elements images_table = doc.select(".images_table");
            Elements trs = images_table.select("tbody tr");
            int i = 0;
            for (Element tr:trs){
                Elements tds = tr.select("td");
                for(Element td:tds){
                    ImageResult imageResult = new ImageResult();
                    //设置搜索引擎
                    imageResult.setSearchEngine("谷歌搜索");
                    //设置url
                    imageResult.setUrl(td.select("img").attr("src"));
                    //设置hostUrl
                    imageResult.setHostUrl(td.select("a").attr("href").substring(7));
                    //设置title
                    String html = td.html();
                    int titleBegin = html.lastIndexOf("</cite><br>")+11;
                    int tileEnd = html.lastIndexOf("<br>");
                    imageResult.setTitle(html.substring(titleBegin,tileEnd).replace("</b>"," ").replace("<b>"," "));
                    //设置height width
                    Pattern p1 = Pattern.compile("\\d{1,}\\s×\\s\\d{1,}");
                    Matcher m1 = p1.matcher(html);
                    if(m1.find()) {
                        String[] size = m1.group().toString().split(" ");
                        imageResult.setWidth(Integer.parseInt(size[0]));
                        imageResult.setHeight(Integer.parseInt(size[2]));
                    }
                    //设置type
                    int typeBegin = html.lastIndexOf("&nbsp;-&nbsp;")+13;
                    String type = html.substring(typeBegin,html.length());
                    imageResult.setType(type);
                    //设置score
                    imageResult.setScore(AssessScore.assess(i++,"google"));
                    resultList.add(imageResult);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
