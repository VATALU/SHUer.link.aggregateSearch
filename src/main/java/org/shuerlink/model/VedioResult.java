package org.shuerlink.model;

public class VedioResult  extends Result {
    private String time;
    private String imageUrl;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return searchEngine + " " + score + " " + title + "\n" + url + "\n";
    }
}
