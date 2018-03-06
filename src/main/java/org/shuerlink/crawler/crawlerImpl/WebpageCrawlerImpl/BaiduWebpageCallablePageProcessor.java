package org.shuerlink.crawler.crawlerImpl.WebpageCrawlerImpl;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.WebPageCallablePageProcessor;
import org.shuerlink.model.Result.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

@Repository
public class BaiduWebpageCallablePageProcessor extends WebPageCallablePageProcessor {

    private static final String url = "http://www.baidu.com/s?";

    @Override
    public LinkedList<WebPageResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<WebPageResult> process(Document document) {
        LinkedList<WebPageResult> resultList = new LinkedList<>();
        //result-op.c-container.xpath-log
        Elements result_op_c_container_xpath_log = document.select("div.result-op.c-container.xpath-log");
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
                webPageResult.setDescription(p.text());
                //设置评分
                webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                resultList.add(webPageResult);
            }
            Elements tiebaDiscription = result.select("div.op-tieba-general-main-col.op-tieba-general-main-con");
            if (!tiebaDiscription.html().equals("")) {
                //设置摘要
                webPageResult.setDescription(
                        tiebaDiscription.select("p").text());
                //设置评分
                webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                resultList.add(webPageResult);
            }
        }
        //result-op.c-container

        //result.c-container
        Elements results_c_container = document.select("div.result.c-container");
        for (Element result : results_c_container) {
            WebPageResult webPageResult = new WebPageResult();
            //设置搜索引擎
            webPageResult.setSearchEngine("百度");
            //设置标题和标题链接
            Elements title = result.select("h3");
            webPageResult.setTitle(title.text());
            webPageResult.setUrl(title.select("a[href]").attr("href"));
            //设置摘要
            if (!result.select(".c-abstract").text().equals("")) {
                webPageResult.setDescription(result.select(".c-abstract").text());
            } else {
                webPageResult.setDescription(result.select("font p").text().replace("<em>", "").replace("</em>", ""));
            }
            //设置评分
            webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
            resultList.add(webPageResult);
        }
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "wd=" + keyword + "&pn=" + start + "&rn=" + num;
    }

}
