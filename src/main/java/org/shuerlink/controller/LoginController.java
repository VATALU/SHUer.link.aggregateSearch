package org.shuerlink.controller;

import org.shuerlink.model.Student.StudentInfo;
import org.shuerlink.serviceImpl.SHULoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {
    @Resource
    private SHULoginServiceImpl loginService;

//    @requestmapping(value = "/login", produces = "application/json; charset=utf-8", method = requestmethod.post)
//    public @responsebody
//    studentinfo loginstudent(string username, string password) {
//        studentinfo studentinfo = loginservice.loginshustudent(username, password);
//        return studentinfo;
//    }
}
