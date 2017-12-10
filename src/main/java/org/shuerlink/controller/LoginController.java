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

    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody
    StudentInfo loginStudent(String userName, String password) {
        StudentInfo studentInfo = loginService.loginSHUStudent(userName, password);
        return studentInfo;
    }
}
