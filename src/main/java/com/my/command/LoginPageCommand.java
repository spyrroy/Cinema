package com.my.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginPageCommand extends Command {
    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return "WEB-INF/login.jsp";
    }
}
