package com.my.service;

import com.my.entity.User;
import com.my.exception.DbException;

public interface UserService {
    void add(User user) throws DbException;

    User getById(int id);
    User getByLogin(String login);

    boolean verifyUserPassword(String login, String password);
}
