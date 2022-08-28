package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")},
        dispatcherTypes = {DispatcherType.REQUEST}
)
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("before chain ");
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("from doFilter Encoding = " + req.getCharacterEncoding());
        if (req.getCharacterEncoding() == null) {
            System.out.println("set encoding " + encoding);
            req.setCharacterEncoding(encoding);
            ((HttpServletResponse) response).sendRedirect("index.html");
            return;
        }

        chain.doFilter(request, response);
//        System.out.println("after chain ");
    }
}
