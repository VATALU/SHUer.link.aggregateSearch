package org.shuerlink.model;

public class ImageResult extends Result {

    private String hostUrl;
    private String extension;
    private int height;
    private int width;

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "\n" + searchEngine + " " + score + " " + title + "\n" + url + "\n" + hostUrl + "\n" + width + "*" + height + extension;
    }
}
