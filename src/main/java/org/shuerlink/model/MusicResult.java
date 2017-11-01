package org.shuerlink.model;

public class MusicResult extends Result {
    private String singer;
    private String singerUrl;
    private String album;

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    private String albumUrl;

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSingerUrl() {
        return singerUrl;
    }

    public void setSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return searchEngine + " " + score + " " + title + "\n" + url + "\n" + singer + "\n" + singerUrl + "\n";
    }
}

