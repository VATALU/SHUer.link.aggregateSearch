package org.shuerlink.crawler;

import org.shuerlink.model.VedioResult;

import java.util.LinkedList;

public interface VedioCrawler {
    public LinkedList<VedioResult> getVedioResult(String keyword);
}
