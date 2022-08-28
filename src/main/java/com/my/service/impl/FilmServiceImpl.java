package com.my.service.impl;

import com.my.DAO.FilmDAO;
import com.my.entity.Film;
import com.my.exception.DbException;
import com.my.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    private static final Logger LOG = LoggerFactory.getLogger(FilmServiceImpl.class);
    private FilmDAO filmDAO;

    public FilmServiceImpl(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }


    @Override
    public void add(Film film) throws DbException {
        LOG.debug("Adding film: {} ", film);
        LOG.debug("Adding film: {} ", film.toString());
        boolean status = filmDAO.addFilm(film);
        System.out.println("Status - " + status);

    }

    @Override
    public List<Film> getAll() {
        return filmDAO.getAllFilms();
    }

    @Override
    public void delete(int id) {
        filmDAO.deleteFilmById(id);
    }

    @Override
    public void update(Film film) {;
        filmDAO.updateFilm(film);
    }

    @Override
    public Film getById(int id) {
        return filmDAO.getFilmById(id);
    }
}
