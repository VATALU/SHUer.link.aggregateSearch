package org.shuerlink.crawlerImpl.ShareCrawlerImpl;

import org.shuerlink.crawler.ShareCallablePageProcessor;
import org.shuerlink.model.ShareResult;
import us.codecraft.webmagic.Page;
import org.jsoup.nodes.Document;

import java.util.LinkedList;

public class JianshuCallablePageProcessor extends ShareCallablePageProcessor {
    @Override
    public LinkedList<ShareResult> getResults(Page page) {
        return null;
    }

    public LinkedList<ShareResult> process(Document document){
        LinkedList<ShareResult> shareResults = null;
        return shareResults;
    }

    @Override
    public String getUrl(String keyword, int start, int num) {
        return "http://www.jianshu.com/search?q=" + keyword + "&page=" + (start / 10 + 1) + "&type=note";
    }
}
