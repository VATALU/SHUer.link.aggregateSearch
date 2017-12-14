package org.shuerlink.client;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.model.Student.StudentInfo;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class ServiceClient  {
    private static final Logger logger =  Logger.getLogger(ServiceClient.class.getName());
    private static final String host = "http://services.shu.edu.cn/";

    public boolean login(String userName, String password,StudentInfo studentInfo) throws IOException {
        Connection.Response response = Jsoup.connect(host + "/login.aspx")
                .data("__EVENTTARGET", "")
                .data("__EVENTARGUMENT", "")
                .data("txtUserName", userName)
                .data("txtPassword", password)
                .data("btnOk", "提交(Submit)")
                .userAgent("Mozilla").timeout(1000 * 10).ignoreContentType(true).execute();

        String html = response.body();
        if (html.contains("用户名密码错误!") || html.contains("系统出错了") || html.contains("工号")) {
//            logger.info(userName+" 登陆失败");
            return false;
        } else {
//            logger.info(userName+" 登陆成功");
            studentInfo.setLogined(true);
            Map<String, String> cookies = response.cookies();
            getData(studentInfo,cookies);
            System.out.println(studentInfo);
            return true;
        }
    }

    public void getData(StudentInfo studentInfo, Map<String, String> cookies) throws IOException {
        Document document = Jsoup.connect(host + "/User/userPerInfo.aspx")
                .cookies(cookies)
                .timeout(1000)
                .userAgent("Mozilla")
                .get();
        String name = document.select("#userName").text();
        String nickname = document.select("#nickname").text();
        studentInfo.setName(name);
        studentInfo.setNickname(nickname);
    }

}
