package TPOTask7.Filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "header", urlPatterns = {"*.jsp"})
public class HeaderFilter implements Filter {

    private static final String HEADER = "<html><head>" //
            + "<title>CALCULATOR</title>" //
            + "<div>HEADER</div>" //
            + "<script src=\"https://code.jquery.com/jquery-latest.min.js\"\"></script>" //
            + "<script src=\"index.js\"></script>" //
            + "</head><body>";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter writer = servletResponse.getWriter();
        writer.write(HEADER);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
