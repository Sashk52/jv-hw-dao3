package taxi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't found MySQL driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "1234");
        String url = "jdbc:mysql://localhost:3306/taxi_service?serverTimezone=UTС";
        try {
            Connection connection = DriverManager.getConnection(url, dbProperties);
            System.out.println("Connection to db established");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish the connection to db", e);
        }
    }
}
