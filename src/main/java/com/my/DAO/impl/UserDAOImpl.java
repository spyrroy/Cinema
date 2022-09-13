package com.my.DAO.impl;

import com.my.DAO.UserDAO;
import com.my.DBManager;
import com.my.entity.Role;
import com.my.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String GET_USER_BY_ID = "select * from user JOIN role r on user.role_id =r.role_id where user_id = ?";
    private static final String GET_SALT_BY_LOGIN = "SELECT salt FROM user WHERE login = ?";
    private static final String GET_PASSWORD_BY_LOGIN = "SELECT password FROM user WHERE login = ?";
    private static final String GET_USER_BY_LOGIN = "select * from user JOIN role r on user.role_id =r.role_id where login = ?";
    private final String GET_ALL_USERS = "SELECT u.user_id, u.login, u.first_name, u.second_name, u.password, r.role " +
            "FROM user u " +
            "INNER JOIN role r on u.role_id =r.role_id";
    private final String ADD_USER = "INSERT INTO user (user_id, login, first_name, second_name, password, email, salt, role_id) " +
            "VALUES (default, ?, ?, ?, ?, ?, ?, 2)";

    private final DataSource ds;

    public UserDAOImpl(DataSource ds) {
        this.ds = ds;
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(GET_ALL_USERS)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setFirst_name(rs.getString("first_name"));
                user.setSecond_name(rs.getString("second_name"));
                user.setPassword(rs.getString("password"));
                user.setRole(new Role(rs.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setString(3, user.getSecond_name());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getSalt());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String firstname = resultSet.getString("first_name");
                String secondname = resultSet.getString("second_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String salt = resultSet.getString("salt");
                Role role = new Role(resultSet.getString("role"));
                user = new User(login, firstname, secondname, password, email, salt, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstname = resultSet.getString("first_name");
                String secondname = resultSet.getString("second_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String salt = resultSet.getString("salt");
                Role role = new Role(resultSet.getString("role"));
                user = new User(id, login, firstname, secondname, password, email, salt, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public String getSaltByLogin(String login) {
        String salt = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SALT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salt = resultSet.getString("salt");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salt;
    }

    @Override
    public String getPasswordByLogin(String login) {
        String password = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }
}
