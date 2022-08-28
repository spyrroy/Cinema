package com.my.command;

import com.my.service.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteFilmCommand extends Command {
    private final FilmService filmService;
    public DeleteFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter("id"));
        filmService.delete(id);
        return "redirect:app?cmd=allFilms";
    }
}
