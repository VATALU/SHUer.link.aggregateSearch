package org.shuerlink.model.Student;

public class Shuerlink {
    private boolean autoChangeWallpaper = true;
    private String defaultBackgroundImage = "";
    private String defaultSearchEngine = "Google";
    private WallpaperCategory wallpaperCategory = new WallpaperCategory();
    private String theme = "transparent";
    private String defaultWikiLanguage = "en";
    private String changeWallpaperTime = "everyDay";
    private boolean autoComplete = true;

    public boolean isAutoChangeWallpaper() {
        return autoChangeWallpaper;
    }

    public void setAutoChangeWallpaper(boolean autoChangeWallpaper) {
        this.autoChangeWallpaper = autoChangeWallpaper;
    }

    public String getDefaultBackgroundImage() {
        return defaultBackgroundImage;
    }

    public void setDefaultBackgroundImage(String defaultBackgroundImage) {
        this.defaultBackgroundImage = defaultBackgroundImage;
    }

    public String getDefaultSearchEngine() {
        return defaultSearchEngine;
    }

    public void setDefaultSearchEngine(String defaultSearchEngine) {
        this.defaultSearchEngine = defaultSearchEngine;
    }

    public WallpaperCategory getWallpaperCategory() {
        return wallpaperCategory;
    }

    public void setWallpaperCategory(WallpaperCategory wallpaperCategory) {
        this.wallpaperCategory = wallpaperCategory;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDefaultWikiLanguage() {
        return defaultWikiLanguage;
    }

    public void setDefaultWikiLanguage(String defaultWikiLanguage) {
        this.defaultWikiLanguage = defaultWikiLanguage;
    }

    public String getChangeWallpaperTime() {
        return changeWallpaperTime;
    }

    public void setChangeWallpaperTime(String changeWallpaperTime) {
        this.changeWallpaperTime = changeWallpaperTime;
    }

    public boolean isAutoComplete() {
        return autoComplete;
    }

    public void setAutoComplete(boolean autoComplete) {
        this.autoComplete = autoComplete;
    }
}
