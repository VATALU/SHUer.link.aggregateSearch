package org.shuerlink.serviceImpl;

import org.shuerlink.client.Client;
import org.shuerlink.service.LoginService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {
    public boolean loginSHUStudent (String userName,String password){
        Client client = new Client();
        try {
            return client.loginShuzhi(userName,password);
        } catch (IOException e) {
            try {
                return client.loginService(userName,password);
            } catch (IOException e1) {
                return false;
            }
        }
    }
}
