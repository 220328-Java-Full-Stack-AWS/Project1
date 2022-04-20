package com.revature.servlets;

import com.revature.util.ConnectionFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

public class DependencyLoaderListener implements ServletContextListener {
    Connection conn;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        conn = ConnectionFactory.getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionFactory.close();
    }
}