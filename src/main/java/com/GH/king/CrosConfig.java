package com.GH.king;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 2016/1/25.
 */
@Component
public class CrosConfig implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin","*");
        chain.doFilter(req, res);
    }
    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig arg0) throws ServletException {}
}
