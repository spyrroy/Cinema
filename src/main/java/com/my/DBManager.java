package com.my;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static DBManager instance;

    private MysqlDataSource mysqlDataSource;
    private static final String DB_PROPERTIES = "/database.properties";

    private void setDbProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DBManager.class.getResourceAsStream(DB_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mysqlDataSource.setUser(properties.getProperty("user"));
        mysqlDataSource.setPassword(properties.getProperty("password"));
        mysqlDataSource.setURL(properties.getProperty("url"));
    }

    private DBManager() {
        mysqlDataSource = new MysqlDataSource();
        setDbProperties();
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = mysqlDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}