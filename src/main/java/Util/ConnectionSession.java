package Util;
import java.sql.Connection;


public class ConnectionSession implements AutoCloseable{

    private Connection activeConnection;
    private int locationIndex = -1;

    public Connection getActiveConnection() {
        for (int i = 0; i < ConnectionFactory.CONNECTIONS; i++) {
            Connection conn = ConnectionFactory.getInstance().getConnectionPool()[i];
            if (conn != null) {
                activeConnection = conn;
                ConnectionFactory.getInstance().getConnectionPool()[i] = null;
                locationIndex = i;
                return activeConnection;
            }
        }
        throw new RuntimeException("No Active connections avaliable");
    }

    @Override
    public void close() {
        ConnectionFactory.getInstance().getConnectionPool()[locationIndex]=activeConnection;
        activeConnection = null;
        locationIndex = -1;
    }
}
