package com.my.command;

import com.my.entity.Session;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.List;

public class AddSessionDateFormCommand extends Command {
    private final SessionService sessionService;
    private final FilmService filmService;
    public AddSessionDateFormCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
//        LocalDate date = LocalDate.parse(req.getParameter("date"));
//        List<Session> sessions = sessionService.getSessionsByDate(date);
//        if (sessions.isEmpty()) {
//            req.setAttribute("date", req.getParameter("date"));
//            return "app?cmd=addSessionForm";
//        }
//        req.setAttribute("sessions", sessions);
////        req.setAttribute("date", sessions);
////        return "app?cmd=addSessionForm";
//        return "WEB-INF/allSessions.jsp";
        return "WEB-INF/addSessionDateForm.jsp";
    }
}
