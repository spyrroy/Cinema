package com.my.command;

import com.my.entity.Film;
import com.my.exception.DbException;
import com.my.service.FilmService;
import com.my.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddFilmCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(AddFilmCommand.class);
    private final FilmService filmService;
    private final GenreService genreService;
    public AddFilmCommand(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int duration = Integer.parseInt(req.getParameter("duration"));
        int genreId = Integer.parseInt(req.getParameter("genre"));
        Film film = new Film(name, description, duration, genreService.getGenreById(genreId));

        try {
            filmService.add(film);
            LOG.debug("Film added: {}", film);
        } catch (DbException e) {
            LOG.error("Cannot add film with name: {}", name);
            throw new CommandException("Cannot add film with name: {}" + name, e);
        }
        return "redirect:app?cmd=allFilms";
    }
}
