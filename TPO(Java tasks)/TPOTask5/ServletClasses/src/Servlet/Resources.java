package Servlet;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class Resources {
    private static final Map<String, String> mapOfResources = new HashMap<>();
    private static Resources resources;

    static {
        try {
            Connection con = InitializeConnection.getCon();
            ResultSet rs = con.createStatement().executeQuery("select * from resources");
            while (rs.next())
                mapOfResources.put(rs.getString("content"), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Resources() {}

    public static Resources getInstance(){
        if(resources == null) resources = new Resources();
        return resources;
    }

    public static Map<String, String> getMapOfResources() {
        return mapOfResources;
    }
}
