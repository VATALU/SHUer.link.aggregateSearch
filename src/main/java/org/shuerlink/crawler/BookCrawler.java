package org.shuerlink.crawler;

import org.shuerlink.model.BookResult;

import java.util.LinkedList;

public interface BookCrawler {
    public LinkedList<BookResult> getBookResult(String keyword, int start, int num);
}
