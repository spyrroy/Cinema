package com.my.command;

import com.my.entity.Film;
import com.my.service.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AllFilmsCommand extends Command {
    private final FilmService filmService;
    public AllFilmsCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        List<Film> films = filmService.getAll();
        req.setAttribute("films", films);
        return "WEB-INF/allFilms2.jsp";
    }
}
