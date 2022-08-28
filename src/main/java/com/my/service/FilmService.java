package com.my.service;

import com.my.entity.Film;
import com.my.exception.DbException;

import java.util.List;

public interface FilmService {
    void add(Film film) throws DbException;
    List<Film> getAll();
    void delete(int id);
    void update(Film film);
    Film getById(int id);
}
