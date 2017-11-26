package org.shuerlink.crawler;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.model.MusicResult;

import java.util.LinkedList;

public abstract class MusicCrawler extends Crawler<MusicResult>{
    public abstract LinkedList<MusicResult> process(Document document);
}
