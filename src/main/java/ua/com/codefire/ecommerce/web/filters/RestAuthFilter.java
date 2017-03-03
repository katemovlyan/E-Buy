package ua.com.codefire.ecommerce.web.filters;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ankys on 15.02.2017.
 */
@WebFilter("/rest/*")
public class RestAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(!httpServletRequest.getServletPath().contains("token")) {
            String token = httpServletRequest.getHeader("Token");

            HttpSession session = httpServletRequest.getSession(true);

            if(token != null && session != null && token.equals(session.getId())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).setStatus(401);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
