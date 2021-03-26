package Util;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements Closeable {

    public static final int CONNECTIONS = 1;
    private final Connection[] connectionPool = new Connection[CONNECTIONS];
    private static ConnectionFactory instance;

    private ConnectionFactory(){
        for (int i = 0; i < CONNECTIONS; i++) {
            connectionPool[i] = createConnection();
        }
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    private Connection createConnection() {
        Properties props = new Properties();
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://samplecar.csuhfolohico.us-west-1.rds.amazonaws.com:5432/postgres?currentSchema=public2",
                    "danrey321",
                    "password");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection[] getConnectionPool() {
        return connectionPool;
    }

    @Override
    public void close() throws IOException {
        for(Connection con: connectionPool){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
