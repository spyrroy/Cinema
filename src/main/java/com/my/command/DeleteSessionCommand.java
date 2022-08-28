package com.my.command;

import com.my.entity.Session;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public class DeleteSessionCommand extends Command {
    private final SessionService sessionService;
    public DeleteSessionCommand(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter("id"));
        Session session = sessionService.getSessionById(id);
        LocalDate date = session.getDate();
        req.setAttribute("date", date);
        sessionService.delete(id);
        return "redirect:app?cmd=allSessions";
    }
}
