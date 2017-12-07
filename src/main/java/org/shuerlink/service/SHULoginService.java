package org.shuerlink.service;

import org.shuerlink.model.Student.Student;

public interface SHULoginService {
    public boolean loginSHUStudent (String userName, String password, Student student);
}
