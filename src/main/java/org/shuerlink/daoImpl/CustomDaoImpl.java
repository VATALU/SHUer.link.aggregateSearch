package org.shuerlink.daoImpl;

import org.shuerlink.dao.CustomDao;
import org.shuerlink.model.Student.Custom;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CustomDaoImpl implements CustomDao{
    @Resource
    private MongoTemplate mongoTemplate;
    @Override
    public Custom findOneById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),Custom.class);
    }

    @Override
    public void save(Custom custom) {
        mongoTemplate.save(custom);
    }
}
