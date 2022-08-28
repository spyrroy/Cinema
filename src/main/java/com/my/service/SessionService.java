package com.my.service;

import com.my.entity.Session;
import com.my.exception.DbException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionService {
    void add(Session session) throws DbException;
    List<Session> getAll();
    List<Session> getSessionsByDate(LocalDate localDate);
    Session getSessionById(int id);
    void update(Session session);
    void delete(int id);
    List<LocalTime> getOccupiedTimesFromSessions(List<Session> sessions);
    List<LocalTime> getAllSessionTimes();
}
