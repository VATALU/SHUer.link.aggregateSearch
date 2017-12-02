package org.shuerlink.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public abstract class CallablePageProcessor implements PageProcessor {
    public abstract <T> List<T> getResults(Page page);

    public abstract String getUrl(String keyword, int start, int num);

    private Site site = Site.me().setSleepTime(0).setTimeOut(3000).setRetryTimes(2).setRetrySleepTime(100).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
    }

}
