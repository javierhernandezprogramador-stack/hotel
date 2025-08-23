package org.jadez.apiservlet.webapp.hotel.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AplicacionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConexionBaseDatos.close();
    }
}
