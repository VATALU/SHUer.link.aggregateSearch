package org.shuerlink.crawler;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.model.BookResult;

import java.util.LinkedList;

public abstract class BookCrawler extends Crawler<BookResult>{
    public abstract LinkedList<BookResult> process(Document document);
}
