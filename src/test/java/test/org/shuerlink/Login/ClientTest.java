package test.org.shuerlink.Login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.shuerlink.client.ServiceClient;
import org.shuerlink.client.ShuzhiClient;
import org.shuerlink.model.Student.StudentInfo;

import java.io.IOException;
import java.util.Map;

public class ClientTest {
    @Test
    public void testLoginShuzhi() {
        String userName = "";
        String password = "";
        ShuzhiClient client = new ShuzhiClient();
        StudentInfo student = new StudentInfo();
        try {
            System.out.println(client.login(userName, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginService() {
        String userName = "";
        String password = "";
        ServiceClient client = new ServiceClient();
        StudentInfo studentInfo = new StudentInfo();
        try {
            Map<String, String> cookies = client.login(userName, password);
            client.getData(studentInfo, cookies);
            System.out.println(studentInfo.getName() + " " + studentInfo.getNickname());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
