package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/hello")
public class EmptyParamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("In init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("In emptyparam filter");
        String inputString = servletRequest.getParameter("input");
        if (inputString != null && inputString.matches("[A-Za-z0-9]+")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse hsr = (HttpServletResponse) servletResponse;
            hsr.setStatus(403);
            servletResponse.getWriter().println("Missing input parameter");
        }
    }

    @Override
    public void destroy() {
        System.out.printf("In destroy");
    }
}
