package filter;

import com.restaurant.dao.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateSessionListener implements ServletRequestListener {


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        ServletRequest request = servletRequestEvent.getServletRequest();
        HibernateUtil.getHibernateUtil().closeSession();
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
