package com.my.DAO.impl;

import com.my.DAO.SeatDAO;
import com.my.DAO.SessionDAO;
import com.my.DAO.TicketDAO;
import com.my.DAO.UserDAO;
import com.my.entity.Seat;
import com.my.entity.Session;
import com.my.entity.Ticket;
import com.my.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    static final Logger LOG = LoggerFactory.getLogger(TicketDAOImpl.class);
    private static final String ADD_NEW_TICKET = "insert into ticket (user_id, seat_id, session_id) values (?, ?, ?)";
    private static final String GET_TICKETS_BY_USER_ID = "SELECT * FROM ticket WHERE user_id = ?";
    private final DataSource ds;

    public TicketDAOImpl(DataSource ds) {
        this.ds = ds;
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    @Override
    public boolean addTicket(Ticket ticket) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, ticket.getUser().getId());
            preparedStatement.setInt(2, ticket.getSeat().getId());
            preparedStatement.setInt(3, ticket.getSession().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                ticket.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Ticket> getTicketsByUserId(int id) {
        List<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(GET_TICKETS_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            UserDAO userDAO = new UserDAOImpl(ds);
            SessionDAO sessionDAO = new SessionDAOImpl(ds);
            SeatDAO seatDAO = new SeatDAOImpl(ds);
            while (resultSet.next()) {
                User user = userDAO.getUserById(resultSet.getInt("user_id"));
                Seat seat = seatDAO.getSeatById(resultSet.getInt("seat_id"));
                Session session = sessionDAO.getSessionById(resultSet.getInt("session_id"));
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                Ticket ticket = new Ticket(id, user, seat, session, date);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return tickets;
    }
}
