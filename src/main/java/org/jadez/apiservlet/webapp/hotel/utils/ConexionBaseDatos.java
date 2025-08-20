package org.jadez.apiservlet.webapp.hotel.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel?serverTimeZone=America/El_Salvador&useUnicode=true&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(USERNAME);
            pool.setPassword(PASSWORD);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }

        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    public static void close() {
        if (pool != null) {
            try {
                pool.close();
                System.out.println("Pool de conexiones cerrado");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
