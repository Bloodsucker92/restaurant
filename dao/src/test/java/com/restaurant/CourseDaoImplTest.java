package com.restaurant;

import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.CourseCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@ContextConfiguration(locations = "classpath*:/beans-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class CourseDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Qualifier("courseDao")
    @Autowired
    CourseBaseDao courseBaseDao;

    @Test
    public void testGetCoursesOfCertainType () throws DaoException {
        ArrayList<Course> list = (ArrayList<Course>) courseBaseDao.getCoursesOfCertainType(1);
        Assert.assertNotNull(list);
        Assert.assertEquals("pizza", list.get(0).getCourseCategory().getCourseCategory());
    }

    @Test
    public void testGetAllCourses () throws DaoException {
        ArrayList<Course> list = (ArrayList<Course>) courseBaseDao.getAllCourses(0, 15, "price", "desc");
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 15);
    }


    @Test
    public void testGetTotalRecordsCount () throws DaoException{
        Assert.assertEquals(courseBaseDao.getTotalRecordsCount(), new Long(21));
    }

    @Test
    public void testGetCourse () throws DaoException {
        Assert.assertEquals(courseBaseDao.get(2).getCourseName(), "margaritta");
        Assert.assertEquals(courseBaseDao.get(2).getCoursePrice(), new Integer(4));
    }

    @Test
    public void testAddUpdateAndDeleteCourse () throws DaoException {
        int courseListSize = courseBaseDao.getAllCourses(0, 30, null, null).size();
        Course course = new Course();
        course.setCourseName("borsh");
        course.setCoursePrice(4);
        course.setImgPath("img.jpg");
        course.setCourseCategory(courseBaseDao.getCourseCategory(3));
        courseBaseDao.add(course);
        Assert.assertEquals(courseListSize+1, courseBaseDao.getAllCourses(0, 30, null, null).size());
        Assert.assertEquals("borsh", courseBaseDao.getAllCourses(0, 30, null, null).get(courseListSize).getCourseName());
        course.setCourseName("holodnik");
        courseBaseDao.update(course);
        Assert.assertEquals("holodnik", courseBaseDao.getAllCourses(0, 30, null, null).get(courseListSize).getCourseName());
        courseBaseDao.delete(course);
        Assert.assertEquals(courseListSize, courseBaseDao.getAllCourses(0, 30, null, null).size());

    }

}
