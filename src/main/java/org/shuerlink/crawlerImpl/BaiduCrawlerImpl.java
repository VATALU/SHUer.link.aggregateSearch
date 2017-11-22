package org.shuerlink.crawlerImpl;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.MusicCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.*;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;


@Repository
public class BaiduCrawlerImpl implements WebPageCrawler, MusicCrawler, ImageCrawler, VedioCrawler {
    private static Logger logger = Logger.getLogger(BaiduCrawlerImpl.class.getName());


    private static String BAIDU = "http://www.baidu.com/s?wd=";
    private static String IMAGE = "http://image.baidu.com/search/index?tn=baiduimage&word=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            resultList = new LinkedList<WebPageResult>();
            Document doc = Jsoup.connect(BAIDU + keyword + "&pn=" + String.valueOf(start) + "&rn=" + String.valueOf(num)).userAgent("Mozilla").timeout(3000).get();

            //result-op.c-container.xpath-log
            Elements result_op_c_container_xpath_log = doc.select("div.result-op.c-container.xpath-log");
            for (Element result : result_op_c_container_xpath_log) {
                WebPageResult webPageResult = new WebPageResult();
                //设置搜索引擎
                webPageResult.setSearchEngine("百度搜索");
                //设置标题和标题链接
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                Elements baikeDiscription = result.select("div.c-span18.c-span-last");
                if (!baikeDiscription.html().equals("")) {
                    //设置摘要
                    Elements p = baikeDiscription.select("p:lt(2)");
                    webPageResult.setDiscription(p.text());
                    //设置评分
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                    resultList.add(webPageResult);
                }
                Elements tiebaDiscription = result.select("div.op-tieba-general-main-col.op-tieba-general-main-con");
                if (!tiebaDiscription.html().equals("")) {
                    //设置摘要
                    webPageResult.setDiscription(
                            tiebaDiscription.select("p").text());
                    //设置评分
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                    resultList.add(webPageResult);
                }
            }
            //result-op.c-container

            //result.c-container
            Elements results_c_container = doc.select("div.result.c-container");
            for (Element result : results_c_container) {
                WebPageResult webPageResult = new WebPageResult();
                //设置搜索引擎
                webPageResult.setSearchEngine("百度搜索");
                //设置标题和标题链接
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                //设置摘要
                webPageResult.setDiscription(result.select(".c-abstract").text());
                //设置评分
                webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                resultList.add(webPageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("百度搜索" + "WebPage" + "搜索失败");
        }
        return resultList;
    }

    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

    /*
    * */
    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start) {
        LinkedList<ImageResult> resultList = null;
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            resultList = new LinkedList<ImageResult>();
            Document doc = Jsoup.connect(IMAGE + keyword + "&pn=" + start).timeout(3000).get();
            String html = doc.html();
            int begin = html.lastIndexOf("'imgData'") + 10;
            int end = html.lastIndexOf("'fcadData'") - 20;
            String subHtml = html.substring(begin, end);
            String text = subHtml.substring(subHtml.indexOf("data") + 6, subHtml.length() - 1);

            //用fastjson将String转换json
            List<BaiduImageResult> baiduImageResults = JSON.parseArray(text, BaiduImageResult.class);
            //去除最后一项多余项
            baiduImageResults.remove(baiduImageResults.size() - 1);
            int i = 0;
            for (BaiduImageResult baiduImageResult : baiduImageResults) {
                ImageResult imageResult = new ImageResult();
                imageResult.setScore(AssessScore.assess(i++, "baidu"));
                imageResult.setTitle(baiduImageResult.getFromURLHost());
                imageResult.setDiscription(baiduImageResult.getFromPageTitle().replace("<strong>", "").replace("</strong>", ""));
                imageResult.setHostUrl(baiduImageResult.getFromURLHost());
                imageResult.setHeight(baiduImageResult.getHeight());
                imageResult.setWidth(baiduImageResult.getWidth());
                imageResult.setType(baiduImageResult.getType());
                imageResult.setUrl(baiduImageResult.getObjURL());
                imageResult.setSearchEngine("百度搜索");
                resultList.add(imageResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public LinkedList<MusicResult> getMusicResult(String keyword, int start, int num) {
        return null;
    }


}
