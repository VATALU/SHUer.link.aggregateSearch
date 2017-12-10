package org.shuerlink.model.Student;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class StudentInfo {
    @Id
    private String id;
    private String name;
    private String nickname;
    private String avatar = "avatar_default.jpg";
    @Transient
    private boolean Logined = false;
    private Custom custom = new Custom();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isLogined() {
        return Logined;
    }

    public void setLogined(boolean logined) {
        Logined = logined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Custom getCustom() {
        return custom;
    }

    public StudentInfo setCustom(Custom custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id='" + id + '\'' +
                ", Logined=" + Logined +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", custom=" + custom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentInfo that = (StudentInfo) o;

        if (Logined != that.Logined) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        return custom != null ? custom.equals(that.custom) : that.custom == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (Logined ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (custom != null ? custom.hashCode() : 0);
        return result;
    }

}


