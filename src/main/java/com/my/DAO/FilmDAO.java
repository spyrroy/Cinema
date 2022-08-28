package com.my.DAO;

import com.my.entity.Film;

import java.util.List;

public interface FilmDAO {
    boolean addFilm(Film film);
    boolean deleteFilmById(int id);
    boolean updateFilm(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
}
