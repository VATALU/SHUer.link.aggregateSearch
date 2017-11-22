package org.shuerlink.model;

public class BaiduImageResult {
    private String objURL;
    private String fromURLHost;
    private int width;
    private int height;
    private String type;
    private String fromPageTitle;

    public String getObjURL() {
        return objURL;
    }

    public void setObjURL(String objURL) {
        this.objURL = objURL;
    }

    public String getFromURLHost() {
        return fromURLHost;
    }

    public void setFromURLHost(String fromURLHost) {
        this.fromURLHost = fromURLHost;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromPageTitle() {
        return fromPageTitle;
    }

    public void setFromPageTitle(String fromPageTitle) {
        this.fromPageTitle = fromPageTitle;
    }

    @Override
    public String toString() {
        return "\n"  + fromPageTitle + "\n" + objURL + "\n" + fromURLHost + "\n" + width + "*" + height + " " + type;
    }
}
