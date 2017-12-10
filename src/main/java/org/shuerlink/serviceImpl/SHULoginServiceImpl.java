package org.shuerlink.serviceImpl;

import org.shuerlink.client.ServiceClient;
import org.shuerlink.client.ShuzhiClient;
import org.shuerlink.daoImpl.StudentDaoImpl;
import org.shuerlink.model.Student.StudentInfo;
import org.shuerlink.service.SHULoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class SHULoginServiceImpl implements SHULoginService {
    @Resource
    private ServiceClient serviceClient;
    @Resource
    private ShuzhiClient shuzhiClient;
    @Resource
    private StudentDaoImpl studentDao;

    public StudentInfo loginSHUStudent(String userName, String password) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(userName);
        try {
            Map<String, String> cookies = serviceClient.login(userName, password);
            if (cookies != null) {
                studentInfo.setLogined(true);
                serviceClient.getData(studentInfo, cookies);
            } else {
                studentInfo.setLogined(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                Map<String, String> cookies = shuzhiClient.login(userName, password);
                if (cookies != null) {
                    studentInfo.setLogined(true);
                    shuzhiClient.getData(studentInfo, cookies);
                } else {
                    studentInfo.setLogined(false);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (studentInfo.isLogined()) {
            StudentInfo dBstudentInfo = studentDao.findOneById(studentInfo.getId());
            if (dBstudentInfo == null) {
                studentDao.save(studentInfo);
            }else{
                studentInfo = dBstudentInfo;
                studentInfo.setLogined(true);
            }
        } else {
            studentInfo.setLogined(false);
        }
        return studentInfo;
    }
}
