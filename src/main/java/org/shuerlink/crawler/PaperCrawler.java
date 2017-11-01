package org.shuerlink.crawler;

import org.shuerlink.model.PaperResult;

import java.util.LinkedList;

public interface PaperCrawler {
    public LinkedList<PaperResult> getPaperReult(String keyword);
}
