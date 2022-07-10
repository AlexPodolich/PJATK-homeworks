package Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/view")

public class ResourcesPage extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse rep) {
        try(var writer = rep.getWriter()){
            Map<String, String> mapOfResources = Resources.getMapOfResources();
            writer.print("<html><body><form> Logged as: " + User.getNameAndSurname(User.getCurrentUser()) +" <br>");
            writer.print("<form> Available resources: <br>");
            ArrayList<String> resources = User.getUserResources(User.getCurrentUser());
            for (var res : mapOfResources.keySet())
                for (int i = 0; i < resources.size(); i++) {
                    if(mapOfResources.get(res).equals(resources.get(i))){
                        writer.print("<a href =" + res + ">" + mapOfResources.get(res) + "</a><br>");
                    }
                }
            if(User.getCurrentUser().equals("Admin")){
                writer.print("<button formaction=\"/database\" formmethod=\"post\" type=\"submit\">Show database</button><br>");
            }
            writer.print("<button formaction=\"/first\" formmethod=\"post\" type=\"submit\">Log out</button>");
            writer.print("</form></html></body>");
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public ResourcesPage() {
    }


}
