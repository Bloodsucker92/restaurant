package com.restaurant;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.userDAO.UserDAO;
import com.restaurant.dao.userDAO.UserDAOImpl;
import com.util.H2ConnectionPool;
import junit.framework.TestCase;
import org.junit.Test;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;


public class UserH2DbTest extends TestCase {
    @Test
    public void testWithH2Db() throws SQLException, IOException, NamingException {
        UserDAO dao = new UserDAOImpl(H2ConnectionPool.getInstance());
        User testUser = dao.addUser("test", "test");
        assertNotNull(testUser);
        assertEquals("test", testUser.getUsername());
        assertEquals("test", testUser.getUserpassword());
        assertEquals("u", testUser.getAccess());
    }

}
