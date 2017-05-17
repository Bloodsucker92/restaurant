package com.restaurant.dao.dao.impl;


import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "courseDaoImpl")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseBaseDao {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(CourseDaoImpl.class);

    @Autowired
    public CourseDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public ArrayList<Course> getCoursesOfCertainType(Integer id) throws DaoException {
        List<Course> list;
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("courseCategory.id", id));
            list = criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(CourseDaoImpl.class, "Error getting course of certain type", e);
        }

        return (ArrayList<Course>) list;
    }

    public ArrayList<Course> getAllCourses(int startPage, int itemsPerPage) throws DaoException {
        List<Course> list;
        try {
            Criteria criteria = createEntityCriteria();
            criteria.setFirstResult(startPage);
            criteria.setMaxResults(itemsPerPage);
            list = criteria.list();
        }
        catch (HibernateException e) {
            throw new DaoException(CourseDaoImpl.class, "Error getting all courses", e);
        }
        return (ArrayList<Course>) list;
    }

    public Long getTotalRecordsCount() throws DaoException {
        Long count;
        try {
            Criteria criteria = createEntityCriteria();
            criteria.setProjection(Projections.rowCount());
            count = (Long) criteria.uniqueResult();
        }
        catch (HibernateException e) {
            throw new DaoException(CourseDaoImpl.class, "Error getting total record count", e);
        }
        return count;
    }

    @Override
    public Course get(Serializable id) throws DaoException {
        try {
            log.info("Get:" + id);
        }
        catch (HibernateException e) {
            throw new DaoException(CourseDaoImpl.class, "Error getting course", e);
        }
        return (Course) getSession().load(Course.class, id);
    }


}
