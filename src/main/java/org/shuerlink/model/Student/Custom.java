package org.shuerlink.model.Student;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "custom")
public class Custom {
    @Id
    private String id;
    private Shuerlink shuerlink = new Shuerlink();

    public Shuerlink getShuerlink() {
        return shuerlink;
    }

    public void setShuerlink(Shuerlink shuerlink) {
        this.shuerlink = shuerlink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Custom custom = (Custom) o;

        if (id != null ? !id.equals(custom.id) : custom.id != null) return false;
        return shuerlink != null ? shuerlink.equals(custom.shuerlink) : custom.shuerlink == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shuerlink != null ? shuerlink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "id='" + id + '\'' +
                ", shuerlink=" + shuerlink +
                '}';
    }

}
