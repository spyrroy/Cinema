package com.my.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);
    private final List<String> guestCommands = new ArrayList<>();
    private final List<String> userCommands = new ArrayList<>();
    private static final List<String> GUEST_COMMANDS = List.of("" ,"allSessions", "login", "loginPage", "register", "registerPage");
    private static final List<String> USER_COMMANDS = List.of("" ,"allSessions", "login", "logout", "loginPage", "register", "registerPage", "addTicketForm", "addTicket", "allTickets");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Enter security filter");
        guestCommands.add(null);
        guestCommands.add("");
        guestCommands.add("allSessions");
        guestCommands.add("login");
        guestCommands.add("loginPage");
        guestCommands.add("register");
        guestCommands.add("registerPage");

        userCommands.add(null);
        userCommands.add("");
        userCommands.add("allSessions");
        userCommands.add("login");
        userCommands.add("logout");
        userCommands.add("loginPage");
        userCommands.add("register");
        userCommands.add("registerPage");
        userCommands.add("addTicketForm");
        userCommands.add("addTicket");
        userCommands.add("allTickets");
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String command = req.getParameter("cmd");
        LOG.debug("SecurityFilter command: {}: ", command);
        LOG.debug("SecurityFilter user role: {}: ", session.getAttribute("userRole"));

        if (session.getAttribute("userRole") == null) {
            LOG.debug("SecurityFilter enter guest if statement: {}: ", session.getAttribute("userRole"));
            if (!guestCommands.contains(command)) {
                LOG.debug("SecurityFilter user can't access this command: {}: ", command);
                resp.sendRedirect("/app?cmd=loginPage");
                return;
            }
        } else if (session.getAttribute("userRole").toString().equals("User")) {
            LOG.debug("SecurityFilter enter user if statement: {}: ", session.getAttribute("userRole"));
            if (!userCommands.contains(command)) {
                resp.sendRedirect("/app?cmd=loginPage");
                return;
            }
        } else {
            LOG.debug("SecurityFilter enter admin else statement: {}: ", session.getAttribute("userRole"));
            filterChain.doFilter(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
