package org.shuerlink.model.Result;

public class ShareResult extends Result {

    private String authorname;
    private String imageUrl;
    private String authorurl;
    private String time;
    private Integer Voters;
    private Integer visitor;
    private Integer comment;
    private String description;

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorurl() {
        return authorurl;
    }

    public void setAuthorurl(String authorurl) {
        this.authorurl = authorurl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getVoters() {
        return Voters;
    }

    public void setVoters(Integer voters) {
        Voters = voters;
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return authorname + " " + authorurl + " " + imageUrl + "\n" +
                title + " " + url + "\n" +
                description + "\n" +
                time + " " + comment + " " + visitor + " " + Voters + "\n";
    }
}
