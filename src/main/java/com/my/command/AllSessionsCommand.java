package com.my.command;

import com.my.entity.Session;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AllSessionsCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(AddSessionCommand.class);
    private final SessionService sessionService;
    private final FilmService filmService;
    public AllSessionsCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        List<Session> sessions = sessionService.getAll();
        req.setAttribute("sessions", sessions);
        return "WEB-INF/allSessions.jsp";
    }
}
