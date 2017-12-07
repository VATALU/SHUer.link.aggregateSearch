package org.shuerlink.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.model.Student.Student;
import org.shuerlink.model.Student.StudentInfo;

import java.io.IOException;
import java.util.Map;

public class ShuzhiClient implements Client {

    private static final String host = "http://www.sz.shu.edu.cn";

    public boolean login(String userName, String password, Student student) throws IOException {
        Connection.Response response = Jsoup.connect(host + "/api/Sys/Users/Client")
                .data("userName", userName)
                .data("password", password)
                .userAgent("Mozilla")
                .timeout(1000 * 10)
                .method(Connection.Method.POST).ignoreContentType(true).execute();
        Map<String, String> stringMap = response.cookies();
        student.setCookie(stringMap);
        JSONObject jsonObject = JSON.parseObject(response.body());
        if (jsonObject.get("message").equals("成功")) {
            student.getStudentInfo().setLogined(true);
            return true;
        } else {
            student.getStudentInfo().setLogined(false);
            return false;
        }
    }


    @Override
    public void getData(Student student) throws IOException {
        Document document = Jsoup.connect(host + "/people/personinfo.aspx")
                .headers(student.getCookie())
                .userAgent("Mozilla")
                .timeout(100)
                .get();
        System.out.println(document.html());
    }

}
