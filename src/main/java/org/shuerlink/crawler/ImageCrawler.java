package org.shuerlink.crawler;

import org.shuerlink.model.ImageResult;

import java.util.LinkedList;

public interface ImageCrawler {
    public LinkedList<ImageResult> getImageResult(String keyword);

}
