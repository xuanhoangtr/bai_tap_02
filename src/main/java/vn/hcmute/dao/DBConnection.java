package vn.hcmute.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String serverName = "localhost";
    private final String dbName = "ShoppingServiceMVC";
    private final int portNumber = 1433;
    private final String userID = "sa";
    private final String password = "123";

    public Connection getConnection() {
        Connection conn = null;
        try {
            try (java.net.Socket socket = new java.net.Socket()) {
                socket.connect(new java.net.InetSocketAddress(serverName, portNumber), 200);
            } catch (Exception notRunning) {
                return null;
            }

            DriverManager.setLoginTimeout(2);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url, userID, password);
        } catch (Exception e) {
            return null;
        }
        return conn;
    }
}
