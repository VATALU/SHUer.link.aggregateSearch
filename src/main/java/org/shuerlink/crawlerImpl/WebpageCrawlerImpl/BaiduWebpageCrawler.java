package org.shuerlink.crawlerImpl.WebpageCrawlerImpl;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.*;
import org.shuerlink.util.AssessScore;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

public class BaiduWebpageCrawler extends WebPageCrawler {

    private static final String url = "http://www.baidu.com/s?";

    private Site site = Site.me().setSleepTime(0).setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(50).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    public static BaiduWebpageCrawler newInstance(String keyword, int start, int num) {
        return new BaiduWebpageCrawler(keyword, start, num);
    }

    private BaiduWebpageCrawler(String keyword, int start, int num) {
        setKeyword(keyword).setStart(String.valueOf(start)).setNum(String.valueOf(num));
    }

    @Override
    public void process(Page page) {
        process(page.getHtml().getDocument());
    }

    @Override
    public Site getSite() {
        return site;
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
        Elements results_c_container = document.select("div.result.c-container");
        for (Element result : results_c_container) {
            WebPageResult webPageResult = new WebPageResult();
            //设置搜索引擎
            webPageResult.setSearchEngine("百度搜索");
            //设置标题和标题链接
            Elements title = result.select("h3");
            webPageResult.setTitle(title.text());
            webPageResult.setUrl(title.select("a[href]").attr("href"));
            //设置摘要
            if (!result.select(".c-abstract").text().equals("")) {
                webPageResult.setDiscription(result.select(".c-abstract").text());
            } else {
                webPageResult.setDiscription(result.select("font p").text().replace("<em>", "").replace("</em>", ""));
            }
            //设置评分
            webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
            resultList.add(webPageResult);
        }
        setWebPageResults(resultList);
        return resultList;
    }

    @Override
    public String getUrl() {
        return url + "wd=" + getKeyword() + "&pn=" + getStart() + "&rn=" + getNum();
    }
}
