package org.shuerlink.crawler;

import org.shuerlink.model.ImageResult;

import java.util.LinkedList;

public abstract class ImageCrawler extends Crawler {
    private LinkedList<ImageResult> imageResults = new LinkedList<>();

    public LinkedList<ImageResult> getImageResults() {
        return imageResults;
    }

    public void setImageResults(LinkedList<ImageResult> imageResults) {
        this.imageResults = imageResults;
    }

    public abstract String getUrl();
}
