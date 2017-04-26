package com.restaurant.service.courses;


import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.CourseCategory;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.CourseDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


/*Class provides methods for accessing dao layer and performing service operations on the data obtained
* Deals with the course logic exclusively
*/

public class CourseService {
    private static Logger log = Logger.getLogger(CourseService.class);
    private static CourseService instance;
    private CourseDaoImpl courseDao = CourseDaoImpl.getInstance();
    private HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private Transaction transaction;
    private Session session;
    private final static Integer PIZZA = 1;
    private final static Integer DRINKS = 2;

    private CourseService() {
    }

    public static CourseService getInstance() {
        if (instance == null) {
            instance = new CourseService();
        }
        return instance;
    }

    //Allows to add a course with fields filled by the user of the app. Returns a course object

    public void addCourse(String courseName, int coursePrice, Integer courseCategoryId) throws DaoException {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCoursePrice(coursePrice);
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
//            hibernateUtil.beginTransaction();
            CourseCategory courseType = (CourseCategory) session.get(CourseCategory.class, courseCategoryId);
            course.setCourseCategory(courseType);
            courseDao.saveOrUpdate(course);
            session.flush();
            transaction.commit();
//            hibernateUtil.endTransaction();
        } catch (HibernateException e) {
            log.error("Error adding new course" + e);
            throw new DaoException(e);
        }
    }

    public boolean deleteCourse(Integer id) throws DaoException {
        boolean ifCourseDeleted = false;
        Course course;
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            course = courseDao.get(id);
            courseDao.delete(course);
            session.flush();
            transaction.commit();
            ifCourseDeleted = true;
        } catch (HibernateException e) {
            log.error("Error deleting course" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return ifCourseDeleted;

    }

    public ArrayList<Course> defineWhatTypeOfCoursesToShow(Integer course) throws DaoException {
        ArrayList<Course> courses = null;
        if (course.equals(PIZZA)) {
            courses = showCoursesOfCertainType(course);
        } else if (course.equals(DRINKS)) {
            courses = showCoursesOfCertainType(course);
        }
        return courses;
    }

    public Course getOneCourse(Integer id) throws DaoException {
        Course course = new Course();
        session = hibernateUtil.getSession();
        transaction = session.beginTransaction();
        try {
            course = courseDao.get(id);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting one course" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return course;

    }


    //Returns the list of all the courses available in the restaurant's menu

    public ArrayList<Course> showCoursesOfCertainType(Integer courseCategoryId) throws DaoException {

        List<Course> courseList = new ArrayList<Course>();
        try {

            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            courseList = courseDao.getCoursesOfCertainType(courseCategoryId);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting all courses of certain type" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return (ArrayList<Course>) courseList;
    }

    public Long getTotalCourseNumber() throws DaoException {
        Long totalCourseNumber = 0L;
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            totalCourseNumber = courseDao.getTotalRecordsCount();
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error trying to count all the items in CourseDao" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return totalCourseNumber;
    }

    public ArrayList<Course> showAllCourses(int firstItemIndex, int itemsPerPage) throws DaoException {
        List<Course> courseList = new ArrayList<Course>();
        try {

            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            courseList = courseDao.getAllCourses(firstItemIndex, itemsPerPage);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting all courses" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return (ArrayList<Course>) courseList;
    }


}
