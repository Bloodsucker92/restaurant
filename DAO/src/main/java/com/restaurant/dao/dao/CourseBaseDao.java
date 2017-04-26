package com.restaurant.dao.dao;


import com.restaurant.dao.exceptions.DaoException;

import java.io.Serializable;

public interface CourseBaseDao<Course> extends BaseDao<Course> {
    @Override
    void saveOrUpdate(Course course) throws DaoException;

    @Override
    Course get(Serializable id) throws DaoException;

    @Override
    Course load(Serializable id) throws DaoException;

    @Override
    void delete(Course course) throws DaoException;
}
