package org.shuerlink.crawler.crawlerImpl.ImageCrawlerImpl;

import com.alibaba.fastjson.JSON;
import org.jsoup.nodes.Document;
import org.shuerlink.crawler.ImageCallablePageProcessor;
import org.shuerlink.model.Result.BaiduImageResult;
import org.shuerlink.model.Result.ImageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;

import java.util.LinkedList;
import java.util.List;

@Repository
public class BaiduImageCallablePageProcessor extends ImageCallablePageProcessor {
    private static final String url = "http://image.baidu.com/search/index?";

    @Override
    public LinkedList<ImageResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }

    public LinkedList<ImageResult> process(Document document) {
        LinkedList<ImageResult> resultList = new LinkedList<ImageResult>();
        String html = document.html();
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
            imageResult.setScore(AssessScore.assessBySearchEngine(i++, "baidu"));
            imageResult.setTitle(baiduImageResult.getFromURLHost());
            imageResult.setDescription(baiduImageResult.getFromPageTitle());
            imageResult.setHostUrl(baiduImageResult.getFromURLHost());
            imageResult.setHeight(baiduImageResult.getHeight());
            imageResult.setWidth(baiduImageResult.getWidth());
            imageResult.setType(baiduImageResult.getType());
            imageResult.setUrl(baiduImageResult.getObjURL());
            imageResult.setSearchEngine("百度");
            resultList.add(imageResult);
        }
        return resultList;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + "tn=baiduimage" + "&word=" + keyword + "&pn=" + start;
    }

}
