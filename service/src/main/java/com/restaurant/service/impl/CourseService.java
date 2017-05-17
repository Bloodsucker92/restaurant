package com.restaurant.service.impl;

import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.ICourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
@Log4j2
public class CourseService extends BaseService<Course> implements ICourseService {

    @Autowired
    private CourseBaseDao courseDao;

    @Override
    public Long getTotalCourseNumber() throws ServiceException {
        try {
            return courseDao.getTotalRecordsCount();
        } catch (DaoException e) {
            log.error("Error performing getting total course number service method", e);
            throw new ServiceException(CourseService.class, "Error performing getting total course number service method", e);
        }
    }

    @Override
    public List<Course> showCoursesOfCertainType(Integer courseCategoryId) throws ServiceException {
        try {
            return courseDao.getCoursesOfCertainType(courseCategoryId);
        } catch (DaoException e) {
            log.error("Error performing show courses of certain type service method", e);
            throw new ServiceException(CourseService.class, "Error performing show courses of certain type service method", e);
        }
    }

    @Override
    public List<Course> getAllCourses(int firstItemIndex, int itemsPerPage) throws ServiceException {
        try {
            return courseDao.getAllCourses(firstItemIndex, itemsPerPage);
        } catch (DaoException e) {
            log.error("Error performing getting all courses service method", e);
            throw new ServiceException(CourseService.class, "Error performing getting all courses service method", e);
        }
    }
}
