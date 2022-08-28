package com.my.DAO.impl;

import com.my.DAO.SeatDAO;
import com.my.entity.Genre;
import com.my.entity.Seat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAOImpl implements SeatDAO {
    static final Logger LOG = LoggerFactory.getLogger(SeatDAOImpl.class);
    private static final String GET_FREE_SEATS_BY_SESSION_ID = "SELECT number FROM seat JOIN session_seats s on seat.seat_id = s.seat_id WHERE free=1 AND session_id = ?";
    private static final String GET_SEAT_BY_ID = "SELECT * FROM seat WHERE seat_id=?";
    private final DataSource ds;

    public SeatDAOImpl(DataSource ds) {
        this.ds = ds;
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    @Override
    public List<Seat> getFreeSeatsBySessionId(int id) {
        List<Seat> freeSeats = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FREE_SEATS_BY_SESSION_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int seatId = resultSet.getInt("number");
                freeSeats.add(new Seat(seatId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return freeSeats;
    }

    @Override
    public Seat getSeatById(int id) {
        Seat seat = null;
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(GET_SEAT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                seat = new Seat(id, resultSet.getInt("number"));
            }
        } catch (SQLException e) {
            LOG.error("Can't find genre with id: " + id, e);
            throw new RuntimeException(e);
        }
        return seat;
    }
}
