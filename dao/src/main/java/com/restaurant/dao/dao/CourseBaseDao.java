package com.restaurant.dao.dao;

import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Course;

import java.io.Serializable;
import java.util.List;

public interface CourseBaseDao extends BaseDao<Course> {

    List<Course> getCoursesOfCertainType(Integer id) throws DaoException;

    List<Course> getAllCourses (int startPage, int itemsPerPage) throws DaoException;

    Long getTotalRecordsCount () throws DaoException;

    @Override
    default Course get(Serializable id) throws DaoException {
        return null;
    }
}
