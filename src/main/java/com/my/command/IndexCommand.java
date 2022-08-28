package com.my.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexCommand extends Command {
    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/index.jsp";
//        return "redirect:app?cmd=nameOfCommand";
    }
}
