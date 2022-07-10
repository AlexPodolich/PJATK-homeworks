package Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorFirstPage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse rep){
        try(var writer = rep.getWriter()){
            writer.print("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>UserDecide</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <form>" +
                    "        <div style=\"color:red\">INCORRECT LOGIN OR PASSWORD</div>"+
                    "        Login : <input type=\"text\" name=\"login\">" +
                    "        Password : <input type=\"text\" name=\"password\">" +
                    "        <button formaction=\"/logged\" formmethod=\"get\" type=\"submit\">Log in</button>" +
                    "    </form>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
