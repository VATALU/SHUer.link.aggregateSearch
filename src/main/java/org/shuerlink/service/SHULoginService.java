package org.shuerlink.service;

import org.shuerlink.model.Student.StudentInfo;

public interface SHULoginService {
    public StudentInfo loginSHUStudent (String userName, String password);
}
