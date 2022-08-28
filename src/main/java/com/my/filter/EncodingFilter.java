package com.my.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(urlPatterns = "/*",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")},
        dispatcherTypes = {DispatcherType.REQUEST}
)
public class EncodingFilter implements Filter {

    static final Logger LOG = LoggerFactory.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
//        LOG.debug("from doFilter Encoding = " + req.getCharacterEncoding());
        if (req.getCharacterEncoding() == null) {
            LOG.debug("set encoding: {} ", encoding);
            req.setCharacterEncoding(encoding);
            ((HttpServletResponse) response).sendRedirect("index.jsp");
            return;
        }

        chain.doFilter(request, response);

    }
}
