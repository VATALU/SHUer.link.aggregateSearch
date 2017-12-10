package org.shuerlink.client;

import org.shuerlink.model.Student.StudentInfo;

import java.io.IOException;
import java.util.Map;

public interface Client {

    public void getData(StudentInfo studentInfo,Map<String, String> cookies) throws IOException;

    public Map<String, String> login(String userName, String password) throws IOException;


}
