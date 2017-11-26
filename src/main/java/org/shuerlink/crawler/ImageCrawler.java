package org.shuerlink.crawler;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.model.ImageResult;

import java.util.LinkedList;

public abstract class ImageCrawler extends Crawler<ImageResult> {
    public abstract LinkedList<ImageResult> process(Document document);
}
