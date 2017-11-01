package org.shuerlink.model;

/**
 * @author VATALU
 */
public class WebPageResult extends Result {
    private String discription;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return searchEngine + " " + score + " " + title + "\n" + url + "\n" + discription +"\n";
    }
}
