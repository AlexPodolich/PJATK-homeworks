import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/hello-servlet")
public class Controller extends HttpServlet {
    Model model = Model.getInstance();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        model.setNum1(Integer.parseInt(request.getParameter("num1")));
        model.setNum2(Integer.parseInt(request.getParameter("num2")));

        model.calculateSum();

        View.view(response, model.getSum());
    }
    public void destroy(){}
}
