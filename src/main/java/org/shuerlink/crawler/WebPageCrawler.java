package org.shuerlink.crawler;

import org.jsoup.nodes.Document;
import org.shuerlink.model.WebPageResult;

import java.util.LinkedList;

public abstract class WebPageCrawler extends Crawler {
    private LinkedList<WebPageResult> webPageResults = new LinkedList<>();

    public LinkedList<WebPageResult> getWebPageResults() {
        return webPageResults;
    }

    public void setWebPageResults(LinkedList<WebPageResult> webPageResults) {
        this.webPageResults = webPageResults;
    }

    public abstract String getUrl();

}
