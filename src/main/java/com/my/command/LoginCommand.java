package com.my.command;

import com.my.entity.User;
import com.my.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (!userService.verifyUserPassword(login,password)) {
            req.setAttribute("errorMessages", "Wrong user or password");
            return "WEB-INF/login.jsp";
        }
        User user = userService.getByLogin(login);
        final HttpSession session = req.getSession(true);
        LOG.debug("User added to session : {}", user);
        session.setAttribute("user", user);
        session.setAttribute("userRole", user.getRole().getRole());
        return "redirect:app";
    }
}
