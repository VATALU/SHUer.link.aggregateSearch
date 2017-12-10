package org.shuerlink.daoImpl;

import org.shuerlink.dao.StudentDao;
import org.shuerlink.model.Student.StudentInfo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StudentDaoImpl implements StudentDao{
    @Resource
    private MongoTemplate mongoTemplate;

    public StudentInfo findOneById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), StudentInfo.class);
    }

    public void save(StudentInfo studentInfo) {
        mongoTemplate.save(studentInfo);
    }

}
