package org.shuerlink.model.Result;

public class VideoResult extends Result {
    private String time;
    private String imageUrl;
    private String publishTime;
    private String publisher;

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

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
        return "\n" + searchEngine + " " + score + " " + title + "\n" +
                url + "\n" +
                imageUrl + "\n" + publisher + " " + publishTime + " " + time;
    }
}
