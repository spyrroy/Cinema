package com.my.DAO;

import com.my.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    List<User> getAllUser();
    boolean addUser(User t);
    User getUserById(int id);
    User getUserByLogin(String login);
    String getSaltByLogin(String login);
    String getPasswordByLogin(String login);
}
