package DAO;

import entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();
    boolean addUser(User t);
}
