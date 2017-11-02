package org.shuerlink.model;

public class BookResult extends Result {
    private String imageUrl;
    private String discription;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return searchEngine + "\n" + score + "\n" + title + "\n" + url + "\n" + discription + "\n" + imageUrl + "\n";
    }
}
