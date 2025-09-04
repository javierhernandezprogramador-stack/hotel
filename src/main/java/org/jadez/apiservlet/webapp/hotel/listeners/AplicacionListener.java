package org.jadez.apiservlet.webapp.hotel.listeners;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;

    //Estos metodos sirven cuando se inicializa o cierra la aplicacion completamente
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Inicializando la aplicacion!");
        servletContext.setAttribute("mensaje", "algun valor global de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("destruyendo la aplicacion!");
    }

    //Estos dos metodos sirven cuando se inicializan o se cierran los request

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para el request!");
        sre.getServletRequest().setAttribute("title", "Catálogo Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo el request!");
    }

    //estos dos metodos sirven cuando se inicializan o se cierran la sessiones

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la session http");
        //queda mas limpio si inicializamos el carro de compra cuando se crear la session
        //Carro carro = new Carro();
        //HttpSession session = se.getSession();
        //session.setAttribute("carro", carro);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruyendo la session http");
    }

}
