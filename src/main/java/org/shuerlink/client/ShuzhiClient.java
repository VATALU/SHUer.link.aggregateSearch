package org.shuerlink.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.model.Student.StudentInfo;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class ShuzhiClient implements Client {

    private static final String host = "http://www.sz.shu.edu.cn";

    @Deprecated
    public Map<String, String> login(String userName, String password) throws IOException {
        Connection.Response response = Jsoup.connect(host + "/api/Sys/Users/Client")
                .data("userName", userName)
                .data("password", password)
                .userAgent("Mozilla")
                .timeout(1000 * 100)
                .method(Connection.Method.POST).ignoreContentType(true).execute();
        JSONObject jsonObject = JSON.parseObject(response.body());
        if (jsonObject.get("message").equals("成功")) {
            Map<String, String> cookies = response.cookies();
            return cookies;
        } else {
            return null;
        }
    }


    @Deprecated
    @Override
    public void getData(StudentInfo studentInfo, Map<String, String> cookies) throws IOException {

    }

}
