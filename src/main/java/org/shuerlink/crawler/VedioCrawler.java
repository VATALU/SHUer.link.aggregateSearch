package org.shuerlink.crawler;

import org.jsoup.nodes.Document;
import org.shuerlink.Spider.Crawler;
import org.shuerlink.model.VedioResult;

import java.util.LinkedList;

public abstract class VedioCrawler extends Crawler<VedioResult> {
    public abstract LinkedList<VedioResult> process(Document document);
}
