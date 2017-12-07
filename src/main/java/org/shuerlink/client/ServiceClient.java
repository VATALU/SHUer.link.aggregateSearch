package org.shuerlink.client;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.model.Student.Student;

import java.io.IOException;
import java.util.Map;

public class ServiceClient implements Client {
    private static final String host = "http://services.shu.edu.cn/";

    @Override
    public void getData(Student student) throws IOException {
        Document document = Jsoup.connect(host+"/User/userPerInfo.aspx")
                .cookies(student.getCookie())
                .timeout(1000)
                .userAgent("Mozilla")
                .get();
        String name = document.select("#userName").text();
        String nickname = document.select("#nickname").text();
        student.getStudentInfo().setName(name);
        student.getStudentInfo().setNickname(nickname);
    }

    public boolean login(String userName, String password, Student student) throws IOException {
        Connection.Response response = Jsoup.connect(host + "/login.aspx")
                .data("__EVENTTARGET", "")
                .data("__EVENTARGUMENT", "")
                .data("txtUserName", userName)
                .data("txtPassword", password)
                .data("btnOk", "提交(Submit)")
                .userAgent("Mozilla").timeout(1000*10).ignoreContentType(true).execute();

        String html = response.body();
        if (html.contains("用户名密码错误!") || html.contains("系统出错了") || html.contains("工号")) {
            student.getStudentInfo().setLogined(false);
            return false;
        } else {
            Map<String, String> stringMap = response.cookies();
            student.setCookie(stringMap);
            student.getStudentInfo().setLogined(true);
            return true;
        }
    }

}
