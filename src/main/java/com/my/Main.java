package com.my;

import com.my.DAO.UserDAO;
import com.my.DAO.impl.UserDAOImpl;
import com.my.entity.User;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection con = DBManager.getInstance().getConnection();
        System.out.println(con);
//        UserDAO userDAO = new UserDAOImpl();
//        List<User> users = userDAO.getAllUser();
//        System.out.println(users);
//        User user1 = new User("superuser", "super", "user", "111");
//        userDAO.addUser(user1);
//        users = userDAO.getAllUser();
//        System.out.println(users);
    }
}
