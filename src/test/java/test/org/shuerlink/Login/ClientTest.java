package test.org.shuerlink.Login;

import org.junit.Test;
import org.shuerlink.client.ServiceClient;
import org.shuerlink.client.ShuzhiClient;
import org.shuerlink.model.Student.Student;

import java.io.IOException;

public class ClientTest {
    @Test
    public void testLoginShuzhi(){
        String userName = "";
        String password = "";
        ShuzhiClient client = new ShuzhiClient();
        Student student = new Student();
        try {
            System.out.println(client.login(userName,password,student));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginService(){
        String userName = "";
        String password = "";
        ServiceClient client = new ServiceClient();
        Student student = new Student();
        try {
            System.out.println(client.login(userName,password,student));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
