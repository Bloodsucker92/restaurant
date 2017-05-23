package com.restaurant;

import com.restaurant.dao.dao.UserBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(locations = "classpath*:/beans-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class UserDaoImplTest {

    @Qualifier("userDao")
    @Autowired
    UserBaseDao userDao;

    @Test
    public void testGetUserByLoginAndPassword () throws DaoException {
        User user = (User) userDao.getUserByLoginAndPassword("user", "123");
        Assert.assertEquals("user", user.getUsername());
        Assert.assertEquals("123", user.getUserpassword());
    }

    @Test
    public void testGetAllUsers () throws DaoException {
        List<User> userList = userDao.getAllUsers();
        Assert.assertNotNull(userList);
        Assert.assertEquals("user", userList.get(0).getUsername());
    }

    @Test(expected = DaoException.class)
    public void testGetAddUpdateAndDeleteUser () throws DaoException {
        User user = new User();
        user.setUsername("paul");
        user.setUserpassword("thebeatles");
        user.setAccess("USER");
        int id = userDao.add(user);
        User userTwo = (User) userDao.get(id);
        Assert.assertNotNull(userDao.get(id));
        Assert.assertEquals("paul", userTwo.getUsername());
        userTwo.setUsername("george");
        userDao.update(userTwo);
        Assert.assertEquals("george", userTwo.getUsername());
        userDao.delete(userTwo);
        Assert.assertNull(userDao.get(id));
    }
}
