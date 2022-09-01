package com.my.DAO.impl;

import com.my.DAO.FilmDAO;
import com.my.DAO.SessionDAO;
import com.my.entity.Film;
import com.my.entity.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionDAOImpl implements SessionDAO {
    private static final String ADD_NEW_SESSION = "insert into session (date, time, film_id) values (?, ?, ?)";
    private static final String GET_ALL_SESSIONS = "select session.session_id, session.date, session.time, film.film_id, film.name, count(*) freeseats\n" +
            "from session_seats\n" +
            "join session session on session_seats.session_id = session.session_id\n" +
            " join film film on film.film_id = session.film_id\n" +
            "                    where free = 1 group by session.date, session.time, film.name ";
//    private static final String GET_ALL_SESSIONS = "SELECT * FROM session s JOIN film f on s.film_id = f.film_id ORDER BY s.date, s.time";
    private static final String GET_SESSIONS_BY_DATE = "select session.session_id, session.date, session.time, film.film_id, film.name, count(*) freeseats\n" +
        "from session_seats\n" +
        "join session session on session_seats.session_id = session.session_id\n" +
        " join film film on film.film_id = session.film_id\n" +
        "                    where free = 1 and session.date = ? group by session.date, session.time, film.name ";
    private static final String GET_SESSION_BY_ID = "select * from session where session_id = ?";
    private static final String SELECT_FREE_SEATS_BY_SESSION = "select count(*) from session_seats where session_id=? and free = 1";
    private static final String DELETE_SESSION_BY_ID = "delete from session where session_id = ?";
    private static final String UPDATE_SESSION_BY_ID = "update session set date = ?, time = ?, film_id = ? where session_id = ?";
    private static final String ORDER_BY_DATE_TIME = "ORDER BY session.date, session.time ";
    private static final String ORDER_BY_FREE_SEATS = "ORDER BY freeseats ";
    private static final String ORDER_BY_FILM_NAME = "ORDER BY film.name ";
    private static final String DESC = "desc";
    private static final String ASC = "asc";
    private final DataSource ds;

    public SessionDAOImpl(DataSource ds) {
        this.ds = ds;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public boolean addSession(Session session) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SESSION, Statement.RETURN_GENERATED_KEYS)) {
            final LocalDate date = session.getDate();
            final LocalTime time = session.getTime();
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setTime(2, Time.valueOf(time));
            preparedStatement.setInt(3, session.getFilm().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                session.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Session> getAllSessions() {
        List<Session> sessions = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SESSIONS + ORDER_BY_DATE_TIME);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            FilmDAO filmDAO = new FilmDAOImpl(ds);
            while (resultSet.next()) {
                int id = resultSet.getInt("session_id");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime time = resultSet.getTime("time").toLocalTime();
                int filmId = resultSet.getInt("film_id");
                Film film = filmDAO.getFilmById(filmId);
                int freeSeats = resultSet.getInt("freeseats");
                Session session = new Session(id, date, time, film);
                session.setFreeSeats(freeSeats);
                sessions.add(session);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }

    @Override
    public List<Session> getAllSessions(Map<String, String> sortingParameters) {
        List<Session> sessions = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildRequest(GET_ALL_SESSIONS, sortingParameters));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            FilmDAO filmDAO = new FilmDAOImpl(ds);
            while (resultSet.next()) {
                int id = resultSet.getInt("session_id");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime time = resultSet.getTime("time").toLocalTime();
                int filmId = resultSet.getInt("film_id");
                Film film = filmDAO.getFilmById(filmId);
                int freeSeats = resultSet.getInt("freeseats");
                Session session = new Session(id, date, time, film);
                session.setFreeSeats(freeSeats);
                sessions.add(session);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }

    @Override
    public List<Session> getAllSessionsOrderByFreeSeats() {
        List<Session> sessions = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SESSIONS + ORDER_BY_FREE_SEATS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            FilmDAO filmDAO = new FilmDAOImpl(ds);
            while (resultSet.next()) {
                int id = resultSet.getInt("session_id");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime time = resultSet.getTime("time").toLocalTime();
                int filmId = resultSet.getInt("film_id");
                Film film = filmDAO.getFilmById(filmId);
                int freeSeats = resultSet.getInt("freeseats");
                Session session = new Session(id, date, time, film);
                session.setFreeSeats(freeSeats);
//                setFreeSeats(session);
                sessions.add(session);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }

    @Override
    public List<Session> getSessionsByDate(LocalDate localDate) {
        List<Session> sessions = new ArrayList<>();
        Date date = java.sql.Date.valueOf(localDate);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SESSIONS_BY_DATE)) {
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            FilmDAO filmDAO = new FilmDAOImpl(ds);
            while (resultSet.next()) {
                int id = resultSet.getInt("session_id");
                LocalTime time = resultSet.getTime("time").toLocalTime();
                int filmId = resultSet.getInt("film_id");
                Film film = filmDAO.getFilmById(filmId);
                int freeSeats = resultSet.getInt("freeseats");
                Session session = new Session(id, localDate, time, film);
                session.setFreeSeats(freeSeats);
                sessions.add(session);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }

    @Override
    public List<Session> getSessionsByDate(LocalDate localDate, Map<String, String> sortingParameters) {
        List<Session> sessions = new ArrayList<>();
        Date date = java.sql.Date.valueOf(localDate);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildRequest(GET_SESSIONS_BY_DATE, sortingParameters))) {
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            FilmDAO filmDAO = new FilmDAOImpl(ds);
            while (resultSet.next()) {
                int id = resultSet.getInt("session_id");
                LocalTime time = resultSet.getTime("time").toLocalTime();
                int filmId = resultSet.getInt("film_id");
                Film film = filmDAO.getFilmById(filmId);
                int freeSeats = resultSet.getInt("freeseats");
                Session session = new Session(id, localDate, time, film);
                session.setFreeSeats(freeSeats);
                sessions.add(session);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }

    @Override
    public int getFreeSeats(Session session) {
        int amount = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FREE_SEATS_BY_SESSION)) {
            preparedStatement.setInt(1, session.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
            session.setFreeSeats(amount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    @Override
    public void setFreeSeats(Session session) {
        int amount = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FREE_SEATS_BY_SESSION)) {
            preparedStatement.setInt(1, session.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
            session.setFreeSeats(amount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSessionById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SESSION_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean updateSession(Session session) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SESSION_BY_ID)) {
            final LocalDate date = session.getDate();
            final LocalTime time = session.getTime();
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setTime(2, Time.valueOf(time));
            preparedStatement.setInt(3, session.getFilm().getId());
            preparedStatement.setInt(4, session.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Session getSessionById(int id) {
        Session session = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SESSION_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            FilmDAO filmDAO = new FilmDAOImpl(ds);
            while (resultSet.next()) {
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime time = resultSet.getTime("time").toLocalTime();
                int filmId = resultSet.getInt("film_id");
                Film film = filmDAO.getFilmById(filmId);
                session = new Session(id, date, time, film);
                setFreeSeats(session);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return session;
    }





    private String buildRequest(String request, Map<String, String> sortingParameters) {
        StringBuilder sb = new StringBuilder(request);
        if (sortingParameters.containsValue("film_name")) {
            sb.append(ORDER_BY_FILM_NAME);
            if (sortingParameters.containsValue("DESC")) {
                sb.append(DESC);
            }
        } else if (sortingParameters.containsValue("free_seats")) {
            sb.append(ORDER_BY_FREE_SEATS);
            if (sortingParameters.containsValue("DESC")) {
                sb.append(DESC);
            }
        } else {
            sb.append(ORDER_BY_DATE_TIME);
            if (sortingParameters.containsValue("DESC")) {
                sb.append(DESC);
            }
        }
        return sb.toString();
    }

}
