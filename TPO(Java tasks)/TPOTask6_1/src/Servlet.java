import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servlet extends HttpServlet
{
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException , ServletException
    {
        String upper = request.getParameter("upper");
        String lower = request.getParameter("lower");
        String result = "";
        try {
            Pattern userPattern = Pattern.compile(upper);
            Matcher matcher = userPattern.matcher(lower);
            if(matcher.matches()){
                result = "<matched-input-part> (start index: " + matcher.regionStart() + ", end index: " + matcher.regionEnd() + ")";
            }else {
                result = "No matches have been found.";
            }
        }catch (Exception e){
            result = "The entered pattern is not compatible with Java regular expression syntax.";
        };
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
