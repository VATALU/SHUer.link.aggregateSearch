package org.shuerlink.model.Result;

/**
 * @author VATALU
 */
public class WebPageResult extends Result {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return searchEngine + " " + score + " " + title + "\n" + url + "\n" + description +"\n";
    }
}
