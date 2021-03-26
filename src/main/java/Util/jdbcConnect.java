package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class jdbcConnect {

    public static Connection getConnection() throws SQLException{

        String url = "jdbc:postgresql://samplecar.csuhfolohico.us-west-1.rds.amazonaws.com:5432/postgres?currentSchema=public2";
        String username = "danrey321";
        String password = "password";

        Connection connection = null;
        //Class.forName("org.postgresql.Driver");
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(url,username, password);


        return connection;
    }

}
