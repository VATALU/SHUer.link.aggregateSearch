package org.shuerlink.model.Result;

public class Result  implements Comparable<Result> {
    protected String searchEngine;
    protected String title;
    protected String url;
    protected double score;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    @Override
    public final int compareTo(Result r) {
        if (r.score < this.score) {
            return 1;
        } else if (r.score > this.score) {
            return -1;
        } else {
            return r.title.compareTo(this.title);
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
        return this.url.equals(((Result)obj).url);
    }
}
