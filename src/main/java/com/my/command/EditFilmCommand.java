package com.my.command;

import com.my.entity.Film;
import com.my.service.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditFilmCommand extends Command {
    private final FilmService filmService;
    public EditFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter("id"));
        Film film = filmService.getById(id);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int duration = Integer.parseInt(req.getParameter("duration"));
        film.setName(name);
        film.setDescription(description);
        film.setDuration(duration);
        filmService.update(film);
        return "redirect:app?cmd=allFilms";
    }
}
