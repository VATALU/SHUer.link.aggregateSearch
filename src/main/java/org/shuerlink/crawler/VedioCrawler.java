package org.shuerlink.crawler;

import org.shuerlink.model.VedioResult;

import java.util.LinkedList;

public abstract class VedioCrawler extends Crawler {
    private LinkedList<VedioResult> vedioResults = new LinkedList<>();

    public LinkedList<VedioResult> getVedioResults() {
        return vedioResults;
    }

    public void setVedioResults(LinkedList<VedioResult> vedioResults) {
        this.vedioResults = vedioResults;
    }

    public abstract String getUrl();

}
