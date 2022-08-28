package com.my.command;

import com.my.entity.Film;
import com.my.entity.Session;
import com.my.exception.DbException;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AddSessionCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(AddSessionCommand.class);
    private final SessionService sessionService;
    private final FilmService filmService;
    public AddSessionCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int filmId = Integer.parseInt(req.getParameter("filmId"));
        Film film = filmService.getById(filmId);
        Session session = new Session();
        session.setTime(LocalTime.parse(req.getParameter("time")));
        session.setDate(LocalDate.parse(req.getParameter("date")));

        session.setFilm(film);

        try {
            sessionService.add(session);
            LOG.debug("Session added: {} ", session);
        } catch (DbException e) {
            LOG.error("Cannot add session with name: {}", session);
            throw new CommandException("Cannot add session with name: {}" + session, e);
        }

        List<Session> sessions = sessionService.getSessionsByDate(LocalDate.parse(req.getParameter("date")));
        req.setAttribute("date", req.getParameter("date"));
        req.setAttribute("sessions", sessions);
//        return "WEB-INF/allSessions.jsp";
        return "redirect:app?cmd=allSessions";
    }
}
