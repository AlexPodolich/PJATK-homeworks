package Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/logged")

public class Controller extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(User.isExist(login, password)){
            ServletContext context = getServletContext();
            var dispatcher = context.getRequestDispatcher("/view");
            User.setCurrentUser(login);
            dispatcher.forward(req,res);
        }else {
            System.out.println("Wrong username or password, try again!");
            ServletContext context = getServletContext();
            var dispatcher = context.getRequestDispatcher("/error");
            dispatcher.forward(req,res);
        }
    }

    public Controller() {
    }
}
