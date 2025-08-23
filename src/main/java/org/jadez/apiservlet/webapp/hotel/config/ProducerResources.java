package org.jadez.apiservlet.webapp.hotel.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.utils.JpaUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResources {

    //Pool de conexiones
    @Resource(name = "jdbc/MysqlDB")
    private DataSource ds;

    //Metodo que servira la conexion en toda la aplicación
    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws SQLException {
        return ds.getConnection();
    }

    //Metodo que cierra la conexion automaticamente cuando el request a terminado
    public void close(@Disposes @MysqlConn Connection conn) throws SQLException {
        conn.close();
    }

    //Metodo que produce los entityManager
    @Produces
    @RequestScoped
    private EntityManager getEntityManager() {
        return JpaUtil.getEntityManager();
    }

    //Metodo que destruye el entityManager
    public void close(@Disposes EntityManager entityManager) {
        if(entityManager.isOpen()) {
            entityManager.close();
        }
    }


}
