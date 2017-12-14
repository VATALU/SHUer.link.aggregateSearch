package org.shuerlink.serviceImpl;

import org.shuerlink.client.ServiceClient;
import org.shuerlink.client.ShuzhiClient;
import org.shuerlink.daoImpl.CustomDaoImpl;
import org.shuerlink.daoImpl.StudentDaoImpl;
import org.shuerlink.model.Student.Custom;
import org.shuerlink.model.Student.StudentInfo;
import org.shuerlink.service.SHULoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class SHULoginServiceImpl implements SHULoginService {
    @Resource
    private ServiceClient serviceClient;
    @Resource
    private ShuzhiClient shuzhiClient;
    @Resource
    private StudentDaoImpl studentDao;
    @Resource
    private CustomDaoImpl customDao;

    public StudentInfo loginSHUStudent(String userName, String password) {
        StudentInfo studentInfo = new StudentInfo();
        try {
            boolean isLogined = serviceClient.login(userName, password, studentInfo);
            if (isLogined) {
                studentInfo.setId(userName);
                Custom custom = customDao.findOneById(userName);
                if(custom != null) {
                    studentInfo.setCustom(custom);
                } else {
                    Custom custom1 = new Custom();
                    custom1.setId(userName);
                    customDao.save(custom1);
                    studentInfo.setCustom(custom1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentInfo;
    }

}
