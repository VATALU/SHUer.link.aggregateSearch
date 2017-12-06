package org.shuerlink.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.shuerlink.model.Student.StudentInfo;

import java.io.IOException;

public class Client {
    private static final String SHUZHI = "http://www.sz.shu.edu.cn";
    private static final String SERVICE = "http://services.shu.edu.cn/";

    public boolean loginShuzhi(String userName, String password) throws IOException {
            Connection.Response response = Jsoup.connect(SHUZHI + "/api/Sys/Users/Client")
                    .data("userName", userName)
                    .data("password", password)
                    .userAgent("Mozilla")
                    .timeout(100)
                    .method(Connection.Method.POST).ignoreContentType(true).execute();
            JSONObject jsonObject = JSON.parseObject(response.body());
            if (jsonObject.get("message").equals("成功")) {
                return true;
            } else {
                return false;
            }
    }

    public void getData(StudentInfo studentInfo){

    }

    public boolean loginService(String userName, String password) throws IOException {
            Document document = Jsoup.connect(SERVICE + "/Client.aspx")
                    .data("__EVENTTARGET", "")
                    .data("__EVENTARGUMENT", "")
                    .data("txtUserName", userName)
                    .data("txtPassword", password)
                    .data("btnOk", "提交(Submit)")
                    .userAgent("Mozilla").timeout(300).post();
            String html = document.html();
            if (html.contains("用户名密码错误!") || html.contains("系统出错了!") || html.contains("工号")) {
                return false;
            } else {
                return true;
            }
    }

}
