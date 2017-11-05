package org.shuerlink.crawlerImpl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.MusicCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.MusicResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

@Repository
public class BaiduCrawlerImpl implements WebPageCrawler, MusicCrawler, ImageCrawler, VedioCrawler {
    private static Logger logger = Logger.getLogger(BaiduCrawlerImpl.class.getName());

    private static String baidu = "http://www.baidu.com/s?wd=";
    private static String image = "http://image.baidu.com/search/index?tn=baiduimage&word=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<WebPageResult>();
            Document doc = Jsoup.connect(baidu + keyword + "&pn=" + String.valueOf(start) + "&rn=" + String.valueOf(num)).userAgent("Mozilla").timeout(3000).get();

            //result-op.c-container.xpath-log
            Elements result_op_c_container_xpath_log = doc.select("div.result-op.c-container.xpath-log");
            for (Element result : result_op_c_container_xpath_log) {
                WebPageResult webPageResult = new WebPageResult();
                webPageResult.setSearchEngine("百度搜索");
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                Elements baike_abstract = result.select("div.c-span18.c-span-last");
                if (!baike_abstract.html().equals("")) {
                    Elements p = baike_abstract.select("p:lt(2)");
                    webPageResult.setDiscription(p.text());
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                    resultList.add(webPageResult);
                }

                Elements tieba_abstract = result.select("div.op-tieba-general-main-col.op-tieba-general-main-con");
                if (!tieba_abstract.html().equals("")) {
                    webPageResult.setDiscription(
                            tieba_abstract.select("p").text());
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                    resultList.add(webPageResult);
                }
            }
            //result-op.c-container

            //result.c-container
            Elements results_c_container = doc.select("div.result.c-container");
            for (Element result : results_c_container) {
                WebPageResult webPageResult = new WebPageResult();
                webPageResult.setSearchEngine("百度搜索");
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                webPageResult.setDiscription(result.select(".c-abstract").text());
                webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                resultList.add(webPageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("百度搜索"+"WebPage"+"搜索失败");
        }
        return resultList;
    }

    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

    /*
    * 图片搜索数据在 app.setData的js代码中
    * 尚未完成
    * */
    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start, int num) {
        LinkedList<ImageResult> resultList = null;
        try {
            resultList = new LinkedList<ImageResult>();
            Document doc = Jsoup.connect(image + keyword).userAgent("Mozilla").timeout(3000).get();
            System.out.println(doc.text());
            Elements imgitem = doc.select("li.imgitem");
            int i = 1;
            for (Element img : imgitem) {
                ImageResult imageResult = new ImageResult();
                imageResult.setSearchEngine("百度搜索");
                String title = img.attr("data-title");
                imageResult.setTitle(title);
                imageResult.setUrl(img.attr("data-thumburl"));
                imageResult.setWidth(Integer.valueOf(img.attr("data-width")));
                imageResult.setHeight(Integer.valueOf(img.attr("data-height")));
                imageResult.setHostUrl(img.attr("data-objurl"));
                imageResult.setExtension(img.attr("data-ext"));
                imageResult.setScore(AssessScore.assess(i++, "baidu"));
                resultList.add(imageResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public LinkedList<MusicResult> getMusicResult(String keyword, int start, int num) {
        return null;
    }


}
