import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class View {
    public static void view(HttpServletResponse response, int sum) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<div>result is " + sum + "</div>");
        out.println("<input type=\"button\" onclick=\"location.href='http://localhost:8080/';\" value=\"Back to calculator\">");
        out.println("</body></html>");
    }
}
