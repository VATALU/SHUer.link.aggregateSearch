package org.shuerlink.model.Student;

public class Custom {
    private Shuerlink shuerlink = new Shuerlink();

    public Shuerlink getShuerlink() {
        return shuerlink;
    }

    public void setShuerlink(Shuerlink shuerlink) {
        this.shuerlink = shuerlink;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "shuerlink=" + shuerlink +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Custom custom = (Custom) o;

        return shuerlink != null ? shuerlink.equals(custom.shuerlink) : custom.shuerlink == null;
    }

    @Override
    public int hashCode() {
        return shuerlink != null ? shuerlink.hashCode() : 0;
    }
}
