package controllers.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by yuri on 25.02.17.
 */
public class SessionFilter implements Filter {
    private Logger logger = Logger.getLogger(SessionFilter.class);

    private ServletContext context;


    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.trace("SessionFilter init");
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.trace("SessionFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        this.context.log("Requested Resource::" + uri);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);

        if (session == null) {
            this.context.log("Unauthorized access request");
            response.sendRedirect("/login");
        } else {
            if (session.getAttribute("id") == null) {
                response.sendRedirect("/login");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        logger.trace("SessionFilter destroy");
    }
}
