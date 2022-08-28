package com.my.service.impl;

import com.my.DAO.SessionDAO;
import com.my.entity.Session;
import com.my.exception.DbException;
import com.my.service.SessionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionServiceImpl implements SessionService {
    private SessionDAO sessionDAO;

    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public void add(Session session) throws DbException {
        sessionDAO.addSession(session);
    }

    @Override
    public List<Session> getAll() {
        return sessionDAO.getAllSessions();
    }

    @Override
    public List<Session> getSessionsByDate(LocalDate localDate) {
        return sessionDAO.getSessionsByDate(localDate);
    }

    @Override
    public Session getSessionById(int id) {
        return sessionDAO.getSessionById(id);
    }

    @Override
    public void update(Session session) {
        sessionDAO.updateSession(session);
    }

    @Override
    public void delete(int id) {
        sessionDAO.deleteSessionById(id);
    }

    @Override
    public List<LocalTime> getOccupiedTimesFromSessions(List<Session> sessions) {
        List<LocalTime> occupiedTimes = new ArrayList<>();
        for (Session session: sessions) {
            LocalTime time = session.getTime();
            occupiedTimes.add(time);
        }
        return occupiedTimes;
    }

    @Override
    public List<LocalTime> getAllSessionTimes() {
        return new ArrayList<>(Arrays.asList(LocalTime.parse("09:00"),
                LocalTime.parse("12:00"),
                LocalTime.parse("16:00"),
                LocalTime.parse("19:00"),
                LocalTime.parse("22:00")));
    }
}
