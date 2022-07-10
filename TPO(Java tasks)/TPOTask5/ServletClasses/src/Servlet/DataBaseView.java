package Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/database")

public class DataBaseView extends HttpServlet {
    public DataBaseView() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse rep) {
        try(var writer = rep.getWriter()){
            writer.print("<html><body><form> Users in database: <br>");
            Connection con = InitializeConnection.getCon();
            ResultSet rs = con.createStatement().executeQuery("select * from users");
            while (rs.next())
                writer.print(
                        "ID: " + rs.getInt(1) + "<br>"
                                + "Login: " + rs.getString("login") + "<br>"
                                + "Password: " + rs.getString("password") + "<br>\n"
                                + "<br>");
            writer.print("<button formaction=\"/view\" formmethod=\"post\" type=\"submit\">Back to resources</button>");
            writer.print("</form></html></body>");
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
}
