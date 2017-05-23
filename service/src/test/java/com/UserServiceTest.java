package com;

import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@ContextConfiguration("/beans-service-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    public void testGetAllUsers () throws ServiceException {
        ArrayList<User> users = (ArrayList<User>) userService.getAllUsers();
        Assert.assertNotNull(users);
    }

    @Test
    public void testFindUserByLoginAndPassword () throws ServiceException {
        User user = userService.findUserByLoginAndPassword("user", "123");
        Assert.assertEquals("user", user.getUsername());
        Assert.assertEquals("123", user.getUserpassword());
    }

    @Test
    public void testGetUserByLogin () throws ServiceException {
        User user = userService.getUserByLogin("user");
        Assert.assertEquals("user", user.getUsername());
    }
}
