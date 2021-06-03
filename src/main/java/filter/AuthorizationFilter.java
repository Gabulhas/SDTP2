package filter;

import entities.UtilizadoresEntity;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/user/*")
public class AuthorizationFilter implements Filter {
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
            if (utilizadoresEntity.getTipo() != null) {
                // user is logged in, continue request
                if (!httpServletRequest.getRequestURI().startsWith(httpServletRequest.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
                    httpServletResponse.setHeader("Cache-Control", "no-cache"); // HTTP 1.1.
                    httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                }
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
