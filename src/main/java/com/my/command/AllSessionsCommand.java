package com.my.command;

import com.my.entity.Session;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AllSessionsCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(AllSessionsCommand.class);
    private final SessionService sessionService;
    private final FilmService filmService;

    public AllSessionsCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String date = req.getParameter("date");
        String orderBy = req.getParameter("orderBy");
        String direction = req.getParameter("direction");

        LOG.debug("Date {}", date);
        LOG.debug("orderBy {}", orderBy);
        LOG.debug("direction {}", direction);

        List<LocalDate> dates = sessionService.getAllDatesFromSessions();
        req.setAttribute("dates", dates);
        LOG.debug("Dates {}", dates);
        List<Session> sessions = sessionService.getSessionsSorted(date, orderBy, direction);
        req.setAttribute("sessions", sessions);
        return "WEB-INF/allSessions.jsp";

    }
}
