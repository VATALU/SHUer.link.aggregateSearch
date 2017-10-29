package org.shuerlink.model;

/**
 * @author VATALU
 */
public class TextResult implements Comparable<TextResult> {
    private String searchEngine;
    private String title;
    private String titleURL;
    private String discription;
    private int grade;


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleURL() {
        return titleURL;
    }

    public void setTitleURL(String titleURL) {
        this.titleURL = titleURL;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public int compareTo(TextResult sr) {
        if (sr.grade < this.grade) {
            return 1;
        } else if (sr.grade > this.grade) {
            return -1;
        } else {
            return sr.title.compareTo(this.title);
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    /*
     * URL去重
     */
    @Override
    public boolean equals(Object obj) {
        TextResult sr = (TextResult) obj;
        if (this.titleURL.equals(sr.titleURL)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {

        return searchEngine + " " + grade + " " + title + "\n" + titleURL + "\n" + discription +"\n";
    }
}
