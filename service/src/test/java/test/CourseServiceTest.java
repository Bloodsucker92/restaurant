package test;


import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.CourseCategory;
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
        ArrayList<Course> list = (ArrayList<Course>) courseService.getAllCourses(0, 20, "price", "desc");
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 20);
    }

    @Test
    public void testGetTotalRecordsCount () throws ServiceException {
        Assert.assertEquals(courseService.getTotalCourseNumber(), new Long(21));
    }

    @Test
    public void testGetCourseCategory () throws ServiceException {
        CourseCategory courseCategory = courseService.getCourseCategory(1);
        Assert.assertEquals("pizza", courseCategory.getCourseCategory());
    }

    @Test
    public void testGetAddUpdateAndDeleteCourse () throws ServiceException {
        Course course = new Course();
        course.setCourseName("water");
        course.setCoursePrice(1);
        course.setCourseCategory(courseService.getCourseCategory(2));
        course.setOrderSet(null);
        int id = courseService.add(course);
        Integer actualId = courseService.get(id).getId();
        Assert.assertEquals(new Integer(id), actualId);
        course = courseService.get(id);
        course.setCoursePrice(2);
        courseService.update(course);
        Assert.assertEquals(new Integer(2), courseService.get(id).getCoursePrice());
        courseService.delete(course);
        Assert.assertNull(courseService.get(id));
    }


}
