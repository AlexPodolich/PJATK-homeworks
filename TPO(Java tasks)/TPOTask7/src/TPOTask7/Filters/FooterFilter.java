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

import TPOTask7.StringResponseWrapper;

@WebFilter(filterName = "footer", urlPatterns = {"*.jsp"})
public class FooterFilter implements Filter{

    private static final String FOOTER = "<div>FOOTER</div>" //
            + "<div id=\"date\"></div>" //
            + "</body></html>";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StringResponseWrapper wrapperResponse = new StringResponseWrapper(servletResponse);

        // we pass our wrapper response to servlet instead of original response
        filterChain.doFilter(servletRequest, wrapperResponse);

        // we append the content written by the servlet to the original response
        appendServletGeneratedResponse(servletResponse, wrapperResponse);

        // we append the footer to the original response
        appendFooter(servletRequest, servletResponse);
    }


    private void appendServletGeneratedResponse(ServletResponse originalResponse, StringResponseWrapper wrapperResponse) throws IOException {
        String wrapperContent = wrapperResponse.content();
        PrintWriter originalResponseWriter = originalResponse.getWriter();
        originalResponseWriter.print(wrapperContent);
    }

    private void appendFooter(ServletRequest request, ServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(FOOTER);
        writer.close();
    }

    @Override
    public void destroy() {

    }
}
