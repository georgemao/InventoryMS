package com;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Allow Cross Domain Javascript requests (CORs)
 * See:
 * https://spring.io/guides/gs/rest-service-cors & https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS
 * Examples:
 * http://nanojuice.net/writing-a-spring-boot-rest-application.html
 * http://www.codingpedia.org/ama/how-to-add-cors-support-on-the-server-side-in-java-with-jersey
 */
@Component
public class SimpleCORSFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;

        // Only allow CORS requests from localhost for now. Use * if we want to allow all sources, but that should never occur
        // Allow all 4 http methods
        // Allow for 3600 seconds
        response.setHeader("Access-Control-Allow-Origin", "http://localhost");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
}
