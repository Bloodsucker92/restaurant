package com;


import com.restaurant.dao.pojos.Course;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.ICourseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration("/beans-service-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest {

    @Autowired
    ICourseService courseService;

    @Test
    public void testShowCoursesOfCertainType () throws ServiceException {
        List<Course> list = courseService.showCoursesOfCertainType(1);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getCourseCategory().getCourseCategory(), "pizza");
    }

    @Test
    public void testGetAllCourses () throws ServiceException {
        ArrayList<Course> list = (ArrayList<Course>) courseService.getAllCourses(0, 20);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 20);
    }

    @Test
    public void testGetTotalRecordsCount () throws ServiceException {
        Assert.assertEquals(courseService.getTotalCourseNumber(), new Long(18));
    }


}
