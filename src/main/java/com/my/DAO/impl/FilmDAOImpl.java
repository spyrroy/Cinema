package com.my.DAO.impl;

import com.my.DAO.FilmDAO;
import com.my.DAO.GenreDAO;
import com.my.entity.Film;

import com.my.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    static final Logger LOG = LoggerFactory.getLogger(FilmDAOImpl.class);
    private static final String ADD_NEW_FILM = "insert into film (name, description, duration, genre_id) values (?, ?, ?, ?)";
    private static final String GET_ALL_FILMS = "select * from film";
    private static final String GET_FILM_BY_ID = "select * from film where film_id = ?";
    private static final String DELETE_FILM_BY_ID = "delete from film where film_id = ?";
    private static final String UPDATE_FILM_BY_ID = "update film set name = ?, description = ?, duration = ?, genre_id = ? where film_id = ?";
    private final DataSource ds;

    public FilmDAOImpl(DataSource ds) {
        this.ds = ds;
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public boolean addFilm(Film film) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_FILM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, film.getName());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getDuration());
            preparedStatement.setInt(4, film.getGenre().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                film.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteFilmById(int id) {
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILM_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean updateFilm(Film film) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILM_BY_ID)) {
            preparedStatement.setString(1, film.getName());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getDuration());
            preparedStatement.setInt(4, film.getGenre().getId());
            preparedStatement.setInt(5, film.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FILMS);
        ResultSet resultSet = preparedStatement.executeQuery()) {
            GenreDAO genreDAO = new GenreDAOImlp(ds);
            while (resultSet.next()) {
                int id = resultSet.getInt("film_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int duration = resultSet.getInt("duration");
                int genreId = resultSet.getInt("genre_id");
                Genre genre = genreDAO.getGenreById(genreId);
                Film film = new Film(id, name, description, duration, genre);
                films.add(film);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }

    @Override
    public Film getFilmById(int id) {
        Film film = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FILM_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            GenreDAO genreDAO = new GenreDAOImlp(ds);
            while (resultSet.next()) {
                int filmId = resultSet.getInt("film_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int duration = resultSet.getInt("duration");
                int genreId = resultSet.getInt("genre_id");
                Genre genre = genreDAO.getGenreById(genreId);
                film = new Film(filmId, name, description, duration, genre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return film;
    }
}
