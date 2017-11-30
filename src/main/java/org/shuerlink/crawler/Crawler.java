package org.shuerlink.crawler;

import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class Crawler implements PageProcessor {
    private String keyword;
    private String start;
    private String num;

    public String getKeyword() {
        return keyword;
    }

    public Crawler setKeyword(String keyword) {

        try {
            this.keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getStart() {
        return start;
    }

    public Crawler setStart(String start) {
        this.start = start;
        return this;
    }

    public String getNum() {
        return num;
    }

    public Crawler setNum(String num) {
        this.num = num;
        return this;
    }
}
