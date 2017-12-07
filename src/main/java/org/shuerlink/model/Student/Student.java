package org.shuerlink.model.Student;

import java.util.Map;

public class Student {
    private StudentInfo studentInfo = new StudentInfo();
    private Map<String,String> cookie;

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public Student setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
        return this;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public Student setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
        return this;
    }
}
