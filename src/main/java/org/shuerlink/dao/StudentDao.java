package org.shuerlink.dao;

import org.shuerlink.model.Student.StudentInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public interface StudentDao {
    public StudentInfo findOneById(String id);

    public void save(StudentInfo studentInfo);
}
