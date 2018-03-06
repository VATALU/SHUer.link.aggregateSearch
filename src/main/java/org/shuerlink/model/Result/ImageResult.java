package org.shuerlink.model.Result;

public class ImageResult extends Result {

    private String description;
    private String hostUrl;
    private String type;
    private int height;
    private int width;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "\n" + searchEngine + " " + score + " " + title + " " + description + "\n" + url + "\n" + hostUrl + "\n" + width + "*" + height + " " + type;
    }
}
