package org.shuerlink.crawlerImpl;

import org.shuerlink.crawler.MusicCrawler;
import org.shuerlink.model.MusicResult;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class QQMusicCrawlerImpl implements MusicCrawler{
    @Override
    public LinkedList<MusicResult> getMusicResult(String keyword, int start, int num) {
        return null;
    }
}
