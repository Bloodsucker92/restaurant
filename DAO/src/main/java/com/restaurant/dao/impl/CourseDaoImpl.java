package com.restaurant.dao.impl;


import com.restaurant.dao.beans.Course;
import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseBaseDao<Course> {

    private static Logger log = Logger.getLogger(CourseDaoImpl.class);
    private static HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private static CourseDaoImpl instance;

    public static CourseDaoImpl getInstance (){
        if (instance == null) {
            instance = new CourseDaoImpl();
        }
        return instance;

    }

    public ArrayList<Course> getCoursesOfCertainType(Integer id) throws DaoException {
        Criteria criteria = getSession().createCriteria(Course.class);
        criteria.add(Restrictions.eq("courseCategory.id", id));
        List<Course> list = criteria.list();
        return (ArrayList<Course>) list;
    }

    public ArrayList<Course> getAllCourses (int startPage, int itemsPerPage) throws DaoException {
        Criteria criteria = getSession().createCriteria(Course.class);
        criteria.setFirstResult(startPage);
        criteria.setMaxResults(itemsPerPage);
        List<Course> list = criteria.list();
        return (ArrayList<Course>) list;
    }

    public Long getTotalRecordsCount () throws DaoException {
        Criteria criteria = getSession().createCriteria(Course.class);
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        return count;
    }

    @Override
    public void saveOrUpdate(Course course) throws DaoException {
        hibernateUtil.getSession().saveOrUpdate(course);

    }

    @Override
    public Course get(Serializable id) throws DaoException {
        Course course = (Course) hibernateUtil.getSession().get(Course.class, id);
        return course;
    }

    @Override
    public Course load(Serializable id) throws DaoException {
        return null;
    }

    @Override
    public void delete(Course course) throws DaoException {
        getSession().delete(course);
    }


    @Override
    public Session getSession() {
        return hibernateUtil.getSession();
    };

}
