package org.shuerlink.crawler;

import org.shuerlink.model.WebPageResult;

import java.util.LinkedList;

public interface WebPageCrawler {
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num);

}
