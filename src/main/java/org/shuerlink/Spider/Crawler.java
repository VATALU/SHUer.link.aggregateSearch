package org.shuerlink.Spider;

import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

public abstract class Crawler<T> {
    private String keyword;

    private String start;

    private String num;

    public abstract Site getSite();

    public abstract LinkedList<T> process(Document document);

    public abstract String getUrl();

    public String getKeyword() {
        return keyword;
    }

    public Crawler<T> setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getStart() {
        return start;
    }

    public Crawler<T> setStart(String start) {
        this.start = start;
        return this;
    }

    public String getNum() {
        return num;
    }

    public Crawler<T> setNum(String num) {
        this.num = num;
        return this;
    }

}
