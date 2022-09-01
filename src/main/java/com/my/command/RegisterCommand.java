package com.my.command;

import com.my.entity.Film;
import com.my.entity.Role;
import com.my.entity.User;
import com.my.exception.DbException;
import com.my.service.UserService;
import com.my.util.PasswordUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterCommand.class);
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter("login");
        LOG.debug("login: {}", login);
        String email = req.getParameter("email");
        LOG.debug("email: {}", email);
        String firstname = req.getParameter("firstname");
        String secondname = req.getParameter("secondname");
        String password = req.getParameter("password");
        LOG.debug("password: {}", password);
        String salt = PasswordUtils.getSalt(30);

        LOG.debug("salt: {}", salt);
        String securePassword = PasswordUtils.generateSecurePassword(password, salt);

        User user = new User(login, firstname, secondname, securePassword, email, salt, new Role("User"));
        LOG.debug("Adding User : {}", user);

        try {
            userService.add(user);
            LOG.debug("User added: {}", user);
            final HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

        } catch (DbException e) {
            LOG.error("Cannot add user with login: {}", login);
            throw new CommandException("Cannot add user with login: {}" + login, e);
        }
        return "redirect:app";
    }
}
