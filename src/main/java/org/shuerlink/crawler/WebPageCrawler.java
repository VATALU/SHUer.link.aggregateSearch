package org.shuerlink.crawler;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.model.WebPageResult;

import java.util.LinkedList;

public abstract class WebPageCrawler extends Crawler<WebPageResult> {
    public abstract LinkedList<WebPageResult> process(Document document);
}
