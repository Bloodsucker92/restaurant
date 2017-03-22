package com.restaurant;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.connectionpool.ConnectionPool;
import com.restaurant.dao.userDAO.UserDAO;
import com.restaurant.dao.userDAO.UserDAOImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest extends TestCase {
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockStatement;
    @Mock
    ResultSet mockResultSet;

    ConnectionPool testConnectionPool;

    @Before
    public void setUp() throws SQLException, NamingException {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStatement);
        doNothing().when(mockStatement).setString(anyInt(), anyString());
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.first()).thenReturn(Boolean.TRUE);
        when(mockResultSet.getString(anyString())).thenReturn(anyString());

        testConnectionPool = new ConnectionPool() {
            @Override
            public Connection getConnection() throws SQLException {
                return mockConn;
            }
        };
    }

    @Test
    public void testGetUser() throws ClassNotFoundException, SQLException, NamingException, IOException {
        UserDAO dao = new UserDAOImpl(testConnectionPool);
        assertNotNull(dao.getUser("test", "test"));
    }

    @Test
    public void testAddUser() throws ClassNotFoundException, SQLException, NamingException, IOException {
        UserDAO dao = new UserDAOImpl(testConnectionPool);
        User testUser = dao.addUser("test", "test");
        assertNotNull(testUser);
        assertNotNull(testUser.getUsername());
        assertNotNull(testUser.getUserpassword());
        assertNotNull(testUser.getAccess());
    }
}
