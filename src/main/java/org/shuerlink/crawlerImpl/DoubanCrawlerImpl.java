package org.shuerlink.crawlerImpl;

import org.shuerlink.crawler.BookCrawler;
import org.shuerlink.model.BookResult;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class DoubanCrawlerImpl implements BookCrawler {
    @Override
    public LinkedList<BookResult> getBookResult(String keyword, int start, int num) {
        return null;
    }
}
