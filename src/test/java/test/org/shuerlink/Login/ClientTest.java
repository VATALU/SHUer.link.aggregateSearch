package test.org.shuerlink.Login;

import org.junit.Test;
import org.shuerlink.client.Client;

import java.io.IOException;

public class ClientTest {
    @Test
    public void testLoginShuzhi(){
        String userName = "";
        String password = "";
        Client client = new Client();
        try {
            System.out.println(client.loginShuzhi(userName,password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginService(){
        String userName = "";
        String password = "";
        Client client = new Client();
        try {
            System.out.println(client.loginService(userName,password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
