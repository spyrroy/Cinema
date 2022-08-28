package com.my.command;

import com.my.entity.Session;
import com.my.service.FilmService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.List;

public class AddSessionCalendarFormCommand extends Command {
    private final SessionService sessionService;
    private final FilmService filmService;
    public AddSessionCalendarFormCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return "WEB-INF/addSessionDateForm.jsp";
    }
}
