package com.restaurant.service.interfaces;

import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.CourseCategory;
import com.restaurant.service.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface ICourseService extends IService<Course> {

    Long getTotalCourseNumber() throws ServiceException;

    List<Course> showCoursesOfCertainType(Integer courseCategoryId) throws ServiceException;

    List<Course> getAllCourses (int firstItemIndex, int itemsPerPage, String sortBy, String sortingOrder) throws ServiceException;

    CourseCategory getCourseCategory (int courseCategoryId) throws ServiceException;

    Course get (Serializable id) throws ServiceException;
}
