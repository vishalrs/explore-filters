package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/hello")
public class UpperCaseFilter implements Filter {
    protected FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        ServletResponse newResponse = response;

        if (request instanceof HttpServletRequest) {
            newResponse = new CharResponseWrapper((HttpServletResponse) response);
        }

        chain.doFilter(request, newResponse);

        if (newResponse instanceof CharResponseWrapper) {
            String text = newResponse.toString();
            if (text != null) {
                text = text.toUpperCase();
                response.getWriter().write(text);
            }
        }
    }
}
