package org.shuerlink.crawlerImpl.VedioCrawlerImpl;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.model.VedioResult;

import java.util.LinkedList;

public class BaiduVedioCrawler extends VedioCrawler{
    @Override
    public Site getSite() {
        return null;
    }

    @Override
    public LinkedList<VedioResult> process(Document document) {
        return null;
    }
}
