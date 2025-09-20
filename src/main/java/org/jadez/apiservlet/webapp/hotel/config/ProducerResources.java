package org.jadez.apiservlet.webapp.hotel.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    //Pool de conexiones
    @Resource(lookup = "java:/MySqlDSHotel")
    private DataSource ds;

    @PersistenceUnit(name = "hotel")
    private EntityManagerFactory emf;

    @Inject
    private Logger log;

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
        log.info("cerrando la conexión a la base de datos!!!");
    }

    //Metodo que produce los entityManager
    @Produces
    @RequestScoped
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //Metodo que destruye el entityManager
    public void close(@Disposes EntityManager entityManager) {
        if(entityManager.isOpen()) {
            entityManager.close();
            log.info("cerrando la conexión del entityManager!!!");
        }
    }

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
