package com.my.command;

import com.my.entity.Film;
import com.my.service.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddFilmFormCommand extends Command {
    private final FilmService filmService;

    public AddFilmFormCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        if (id != null) {
            int filmId = Integer.parseInt(req.getParameter("id"));
            Film film = filmService.getById(filmId);
            req.setAttribute("film", film);
            return "/WEB-INF/editFilmForm.jsp";
        }
        return "/WEB-INF/addFilmForm.jsp";
//        return "redirect:app?cmd=nameOfCommand";
    }
}
