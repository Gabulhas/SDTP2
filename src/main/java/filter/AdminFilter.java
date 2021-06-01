package filter;

import entities.UtilizadoresEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public static final String LOGIN_PAGE = "/login_form.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name
        UtilizadoresEntity utilizadoresEntity = (UtilizadoresEntity) httpServletRequest
                .getSession().getAttribute("user_in_session");

        if (utilizadoresEntity != null) {
            if (utilizadoresEntity.getTipo().equals("admin")) {
                // user is logged in, continue request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath() + LOGIN_PAGE);
            }
        } else {
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + LOGIN_PAGE);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
