package org.shuerlink.model.Student;

public class WallpaperCategory {
    private boolean SHU = false;
    private boolean others = true;

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

    @Override
    public String toString() {
        return "WallpaperCategory{" +
                "SHU=" + SHU +
                ", others=" + others +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallpaperCategory that = (WallpaperCategory) o;

        if (SHU != that.SHU) return false;
        return others == that.others;
    }

    @Override
    public int hashCode() {
        int result = (SHU ? 1 : 0);
        result = 31 * result + (others ? 1 : 0);
        return result;
    }
}