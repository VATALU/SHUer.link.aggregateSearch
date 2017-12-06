package org.shuerlink.model.Student;

public class StudentInfo {
    private boolean isLogined;
    private String name;
    private String avatar;
    private Custom custom;

    public boolean isLogined() {
        return isLogined;
    }

    public StudentInfo setLogined(boolean logined) {
        isLogined = logined;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public StudentInfo setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

}

class Custom {
    private Shuerlink shuerlink;

    public Shuerlink getShuerlink() {
        return shuerlink;
    }

    public void setShuerlink(Shuerlink shuerlink) {
        this.shuerlink = shuerlink;
    }
}

class Shuerlink {
    private boolean autoChangeWallpaper;
    private String defaultBackgroundImage;
    private String defaultSearchEngine;
    private WallpaperCategory wallpaperCategory;
    private String theme;
    private String defaultWikiLanguage;
    private String changeWallpaperTime;
    private String everyDay;
    private boolean autoComplete;

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

    public String getEveryDay() {
        return everyDay;
    }

    public void setEveryDay(String everyDay) {
        this.everyDay = everyDay;
    }

    public boolean isAutoComplete() {
        return autoComplete;
    }

    public void setAutoComplete(boolean autoComplete) {
        this.autoComplete = autoComplete;
    }
}

class WallpaperCategory{
    private boolean SHU;
    private boolean others;

    public boolean isSHU() {
        return SHU;
    }

    public void setSHU(boolean SHU) {
        this.SHU = SHU;
    }

    public boolean isOthers() {
        return others;
    }

    public void setOthers(boolean others) {
        this.others = others;
    }
}
