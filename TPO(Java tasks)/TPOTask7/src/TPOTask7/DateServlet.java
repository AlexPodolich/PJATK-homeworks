package TPOTask7;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/date")
public final class DateServlet extends HttpServlet {

    private static final DateFormat FORMAT;

    static {
        FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static final String DATE = "date";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("{ \"" + DATE + "\": \"" + date() + "\" }");
        writer.close();
    }

    private static String date() {
        Date date = new Date();
        return FORMAT.format(date);
    }
}