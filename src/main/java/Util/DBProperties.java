package Util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBProperties{
    public Connection getConnection(String profile) {
        Properties props = new Properties();
        try {
            props.load(new FileReader(new File("src/main/resources/db.properties")));

            String connectionTemplate = "com.project1.profile.production." + profile;
            return DriverManager.getConnection(
                    props.getProperty(connectionTemplate + ".url"),
                    props.getProperty(connectionTemplate + ".username"),
                    props.getProperty(connectionTemplate + ".password"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) throws SQLException {
        String s = "something1.something2.something3";
        String[] somethings = s.split("\\.");
        System.out.println(somethings[1]);

        System.out.println(new DBProperties().getConnection("dev").isValid(1000));
    }


}
