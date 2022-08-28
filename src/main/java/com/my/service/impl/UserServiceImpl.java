package com.my.service.impl;

import com.my.DAO.UserDAO;
import com.my.entity.User;
import com.my.exception.DbException;
import com.my.service.UserService;
import com.my.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void add(User user) throws DbException {
        LOG.debug("Adding user: {} ", user);
        userDAO.addUser(user);
    }

    @Override
    public User getById(int id) {
        LOG.debug("Getting user with id: {}", id);
        return userDAO.getUserById(id);
    }

    @Override
    public User getByLogin(String login) {
        LOG.debug("Getting user with login: {}", login);
        return userDAO.getUserByLogin(login);
    }

    @Override
    public boolean verifyUserPassword(String login, String password) {
        String salt = userDAO.getSaltByLogin(login);
        String securedPassword = userDAO.getPasswordByLogin(login);
        return PasswordUtils.verifyUserPassword(password, securedPassword, salt);
    }
}
