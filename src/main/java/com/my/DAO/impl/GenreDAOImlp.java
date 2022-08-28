package com.my.DAO.impl;

import com.my.DAO.GenreDAO;
import com.my.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDAOImlp implements GenreDAO {
    static final Logger LOG = LoggerFactory.getLogger(GenreDAOImlp.class);
    private static final String GET_GENRE_BY_ID = "SELECT * FROM genre WHERE genre_id=?";
    private final DataSource ds;

    public GenreDAOImlp(DataSource ds) {
        this.ds = ds;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = null;
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(GET_GENRE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genre = new Genre(id, resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error("Can't find genre with id: " + id, e);
            throw new RuntimeException(e);
        }
        return genre;
    }
}
