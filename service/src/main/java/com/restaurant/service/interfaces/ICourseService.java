package com.restaurant.service.interfaces;

import com.restaurant.dao.pojos.Course;
import com.restaurant.service.exceptions.ServiceException;

import java.util.List;

public interface ICourseService extends IService<Course> {

    Long getTotalCourseNumber() throws ServiceException;

    List<Course> showCoursesOfCertainType(Integer courseCategoryId) throws ServiceException;

    List<Course> getAllCourses (int firstItemIndex, int itemsPerPage) throws ServiceException;

}
