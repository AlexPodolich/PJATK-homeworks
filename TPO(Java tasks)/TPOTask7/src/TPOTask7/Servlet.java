package TPOTask7;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Servlet extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException , ServletException
    {
        Integer num1 = Integer.valueOf(request.getParameter("num1"));
        Integer num2 = Integer.valueOf(request.getParameter("num2"));
        Integer sum = num1 + num2;
        response.getWriter().write("Result: " + sum);
    }
}
