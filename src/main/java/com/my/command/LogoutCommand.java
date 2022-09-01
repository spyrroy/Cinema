package com.my.command;

import com.my.entity.User;
import com.my.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(LogoutCommand.class);

    public LogoutCommand() {
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String login = user.getLogin();
        LOG.debug("User logout with login : {}", login);
        session.invalidate();

        return "redirect:app";
    }
}
