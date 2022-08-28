package com.my.DAO;

import com.my.entity.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionDAO {
    boolean addSession(Session session);
    List<Session> getAllSessions();
    List<Session> getSessionsByDate(LocalDate localDate);
    int getFreeSeats(Session session);
    void setFreeSeats(Session session);
    boolean deleteSessionById(int id);
    boolean updateSession(Session session);
    Session getSessionById(int id);

}
