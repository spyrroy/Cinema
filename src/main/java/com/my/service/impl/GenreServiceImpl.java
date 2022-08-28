package com.my.service;

import com.my.DAO.GenreDAO;
import com.my.entity.Genre;

public class GenreServiceImpl implements GenreService{
    private GenreDAO genreDAO;

    public GenreServiceImpl(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = genreDAO.getGenreById(id);
        return genre;
    }
}
