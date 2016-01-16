package ua.george_nika.simulation.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitContextListener implements ServletContextListener {

    private static String LOGGER_NAME = AppLog.CONTROLLER;
    private static String CLASS_NAME = InitContextListener.class.getSimpleName();

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();
        String contextPath = context.getContextPath();
        context.setAttribute("context", contextPath);

        AppLog.info(LOGGER_NAME, CLASS_NAME, "Program has been started");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        AppLog.info(LOGGER_NAME,CLASS_NAME,"Program has been destroyed");
    }
}
