package org.shuerlink.crawlerImpl.BookCrawlerImpl;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Site;
import org.shuerlink.crawler.BookCrawler;
import org.shuerlink.model.BookResult;

import java.util.LinkedList;

public class DoubanBookCrawler extends BookCrawler{
    @Override
    public Site getSite() {
        return null;
    }

    @Override
    public LinkedList<BookResult> process(Document document) {
        return null;
    }
}
