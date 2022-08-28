package com.my.command;

import com.my.entity.Film;
import com.my.service.FilmService;
import com.my.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditFilmCommand extends Command {
    private final FilmService filmService;
    private final GenreService genreService;
    public EditFilmCommand(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter("id"));
        Film film = filmService.getById(id);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int duration = Integer.parseInt(req.getParameter("duration"));
        int genreId = Integer.parseInt(req.getParameter("genre"));
        film.setName(name);
        film.setDescription(description);
        film.setDuration(duration);
        film.setGenre(genreService.getGenreById(genreId));
        filmService.update(film);
        return "redirect:app?cmd=allFilms";
    }
}
