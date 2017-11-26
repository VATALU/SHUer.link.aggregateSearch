package org.shuerlink.Spider;

import org.jsoup.nodes.Document;

import java.util.LinkedList;

public abstract class Crawler<T> {
    public abstract Site getSite();
    public abstract LinkedList<T> process(Document document);
}
