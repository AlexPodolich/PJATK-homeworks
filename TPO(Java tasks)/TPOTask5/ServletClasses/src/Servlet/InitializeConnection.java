package Servlet;
import java.sql.*;

public class InitializeConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";
    private static Connection con;
    static {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public InitializeConnection() {
        System.out.println("CON NOT NULL");
    }


    public static Connection getCon() {
        return con;
    }


}

