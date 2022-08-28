import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static MysqlDataSource mysqlDataSource;
    private static final String DB_PROPERTIES = "/database.properties";

    private void setDbProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DBManager.class.getResourceAsStream(DB_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mysqlDataSource.setUser(properties.getProperty("user"));
        mysqlDataSource.setPassword(properties.getProperty("password"));
        mysqlDataSource.setURL("url");
    }

    public DBManager() {
        mysqlDataSource = new MysqlDataSource();
        setDbProperties();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = mysqlDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}