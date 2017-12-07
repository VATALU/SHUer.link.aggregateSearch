package org.shuerlink.client;

import org.shuerlink.model.Student.Student;

import java.io.IOException;

public interface Client {

    public void getData(Student student) throws IOException;

    public boolean login(String userName, String password, Student student) throws IOException;


}
