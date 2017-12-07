package org.shuerlink.serviceImpl;

import org.shuerlink.client.ServiceClient;
import org.shuerlink.client.ShuzhiClient;
import org.shuerlink.model.Student.Student;
import org.shuerlink.service.SHULoginService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SHULoginServiceImpl implements SHULoginService {
    public boolean loginSHUStudent(String userName, String password, Student student) {
        try {
            ServiceClient serviceClient = new ServiceClient();
            boolean isLogined = serviceClient.login(userName, password, student);
            if(isLogined){
                serviceClient.getData(student);
            }
            return isLogined;
        } catch (IOException e) {
            try {
                ShuzhiClient shuzhiClient = new ShuzhiClient();
                boolean isLogined = shuzhiClient.login(userName, password, student);
                if(!isLogined){
                    shuzhiClient.getData(student);
                }
                return isLogined;
            } catch (IOException e1) {
                return false;
            }
        }
    }
}
