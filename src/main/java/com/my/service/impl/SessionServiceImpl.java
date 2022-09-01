package com.my.service.impl;

import com.my.DAO.SessionDAO;
import com.my.entity.Session;
import com.my.exception.DbException;
import com.my.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class SessionServiceImpl implements SessionService {
    private static final Logger LOG = LoggerFactory.getLogger(SessionServiceImpl.class);
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
    public List<Session> getSessionsSorted(String date, String orderBy, String direction) {
        LOG.debug("Entered getSessionsSorted method ");
        Map<String, String> sortingParameters = new HashMap<>();
        sortingParameters.put("orderBy", orderBy);
        sortingParameters.put("direction", direction);
        List<Session> sessions;
//        if (orderBy != null) {
//
//            if (orderBy.equals("free_seats")) {
//                LOG.debug("Entered getSessionsSorted method if order_by ");
//                sessions = sessionDAO.getAllSessionsOrderByFreeSeats();
//                return sessions;
//            }
//        }
        if (date == null || date.isEmpty()) {
            LOG.debug("Entered getSessionsSorted method if date ");
            sessions = sessionDAO.getAllSessions(sortingParameters);
            return sessions;
        }

        sessions = sessionDAO.getSessionsByDate(LocalDate.parse(date), sortingParameters);
        return sessions;
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
        for (Session session : sessions) {
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

    @Override
    public List<LocalDate> getDatesFromSessions(List<Session> sessions) {
        List<LocalDate> dates = new ArrayList<>();
        for (Session session : sessions) {
            LocalDate date = session.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        return dates;
    }

    @Override
    public List<LocalDate> getAllDatesFromSessions() {
        List<LocalDate> dates = new ArrayList<>();
        List<Session> sessions = getAll();
        for (Session session : sessions) {
            LocalDate date = session.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        return dates;
    }
}
