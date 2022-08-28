package com.my.command;

import com.my.entity.Film;
import com.my.entity.Session;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AddSessionFormCommand extends Command {
    private final SessionService sessionService;
    private final FilmService filmService;

    public AddSessionFormCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String date = req.getParameter("date");
        req.setAttribute("date", date);
        List<Film> films = filmService.getAll();
        req.setAttribute("films", films);
        List<LocalTime> times = sessionService.getAllSessionTimes();
        req.setAttribute("times", times);

        List<Session> sessions = sessionService.getSessionsByDate(LocalDate.parse(date));
        if (!sessions.isEmpty()) {
            List<LocalTime> occupiedTimes = sessionService.getOccupiedTimesFromSessions(sessions);
            req.setAttribute("occupiedTimes", occupiedTimes);
        }
        return "/WEB-INF/addSessionForm.jsp";
    }
}
