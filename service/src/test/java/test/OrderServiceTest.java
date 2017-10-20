package test;

import com.restaurant.dao.pojos.Order;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IOrderService;
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
public class OrderServiceTest {

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;

    @Test
    public void testGetAllOrders () throws ServiceException {
        ArrayList<Order> orderList = (ArrayList<Order>) orderService.getOrders();
        Assert.assertNotNull(orderList);
    }

    @Test
    public void testMakeOrder () throws ServiceException {
        User user = userService.getUserByLogin("user");
        Order order = orderService.makeNewOrder(user, 1);
        Assert.assertNotNull(order);
        Assert.assertEquals("user", order.getUser().getUsername());
        Assert.assertEquals(1, order.getCourseList().size());
        orderService.addItemToCurrentOrder(order.getId(), 2);
        order = orderService.getOrderById(order.getId());
        Assert.assertEquals(2, order.getCourseList().size());
    }

    @Test
    public void testDeleteItemFromOrder () throws ServiceException {
        User user = userService.getUserByLogin("denis");
        Order order = orderService.makeNewOrder(user, 4);
        Assert.assertEquals(1, order.getCourseList().size());
        orderService.deleteItemFromOrder(4, order.getId());
        Assert.assertEquals(0, orderService.getOrderById(order.getId()).getCourseList().size());
    }



}
