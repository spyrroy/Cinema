package com.my.command;

import com.my.entity.Film;
import com.my.service.FilmService;
import com.my.service.GenreService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AddSessionCommand extends Command {
    private final SessionService sessionService;
    private final FilmService filmService;
    public AddSessionCommand(SessionService sessionService, FilmService filmService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        List<Film> films = filmService.getAll();
        req.setAttribute("films", films);
        return "/WEB-INF/addSessionForm.jsp";
    }
}
