package org.shuerlink.dao;

import org.shuerlink.model.Student.Custom;

public interface CustomDao {
    public Custom findOneById(String id);
    public void save(Custom custom);
}
