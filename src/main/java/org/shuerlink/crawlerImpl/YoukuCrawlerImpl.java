package org.shuerlink.crawlerImpl;

import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.model.VedioResult;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class YoukuCrawlerImpl implements VedioCrawler{
    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword) {
        return null;
    }
}
