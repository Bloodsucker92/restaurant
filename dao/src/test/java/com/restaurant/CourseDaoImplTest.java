package com.restaurant;

import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Course;
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


import java.util.ArrayList;


@ContextConfiguration(locations = "classpath*:/beans-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Qualifier("courseDao")
    @Autowired
    CourseBaseDao courseBaseDao;


    @Before
    public void initDb () {
        executeSqlScript("pizza_test_data.sql", false);
    }

    @Test
    public void testGetCoursesOfCertainType () throws DaoException {
        ArrayList<Course> list = (ArrayList<Course>) courseBaseDao.getCoursesOfCertainType(2);
        Assert.assertNotNull(list);
        Assert.assertEquals("drink", list.get(0).getCourseCategory().getCourseCategory());
    }

    @Test
    public void testGetAllCourses () throws DaoException {
        ArrayList<Course> list = (ArrayList<Course>) courseBaseDao.getAllCourses(0, 15);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 15);
    }

    @SqlGroup({
            @Sql(value = "someWorkAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(value = "someWorkBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    @Test
    public void testGetTotalRecordsCount () throws DaoException{
        Assert.assertEquals(courseBaseDao.getTotalRecordsCount(), new Long(21));
    }

    @Test
    public void testGetCourse () throws DaoException {
        Assert.assertEquals(courseBaseDao.get(2).getCourseName(), "margaritta");
        Assert.assertEquals(courseBaseDao.get(2).getCoursePrice(), new Integer(4));
    }


}
