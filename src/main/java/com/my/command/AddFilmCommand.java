package com.my.command;

import com.my.entity.Film;
import com.my.exception.DbException;
import com.my.service.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddFilmCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(AddFilmCommand.class);
    private final FilmService filmService;
    public AddFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int duration = Integer.parseInt(req.getParameter("duration"));
        Film film = new Film(name, description, duration);

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
