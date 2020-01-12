package com.asiainfo.common.init;

import com.asiainfo.order.service.OrderService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

public class InitListener implements javax.servlet.ServletContextListener {


    public void contextDestroyed(ServletContextEvent arg0) {

    }
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        OrderService orderService = (OrderService) webApplicationContext.getBean("orderService");
        orderService.initEventMapping();
        orderService.initEventTemplate();
    }

}
