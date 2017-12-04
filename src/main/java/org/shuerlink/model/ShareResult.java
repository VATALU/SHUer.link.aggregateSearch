package org.shuerlink.model;

public class ShareResult extends Result {

    private String authorname;
    private String imageUrl;
    private String authorurl;
    private String time;
    private int Voters;
    private int visitor;
    private int comment;
    private String discription;

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

    public int getVoters() {
        return Voters;
    }

    public void setVoters(int voters) {
        Voters = voters;
    }

    public int getVisitor() {
        return visitor;
    }

    public void setVisitor(int visitor) {
        this.visitor = visitor;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return authorname + " " + authorurl + " " + imageUrl + "\n" +
                title + " " + url + "\n" +
                discription + "\n" +
                time + " " + comment + " " + visitor + " " + Voters + "\n";
    }
}
