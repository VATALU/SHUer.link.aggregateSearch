package org.shuerlink.crawler;

import org.shuerlink.model.MusicResult;

import java.util.LinkedList;

public interface MusicCrawler {
    public LinkedList<MusicResult> getMusicResult(String keyword);
}
