package com.my.service.impl;

import com.my.DAO.SeatDAO;
import com.my.entity.Seat;
import com.my.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SeatServiceImpl implements SeatService {
    private static final Logger LOG = LoggerFactory.getLogger(SeatServiceImpl.class);
    private SeatDAO seatDAO;

    public SeatServiceImpl(SeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

    @Override
    public List<Seat> getFreeSeatsBySessionId(int id) {
        return seatDAO.getFreeSeatsBySessionId(id);
    }

    @Override
    public List<Seat> getSeatListFromArray(String[] ids) {
        if (ids == null || ids.length == 0) {
            throw new RuntimeException("Seat Array is null or empty");
        }
        List<Seat> seats = new ArrayList<>();
        for (String id : ids) {
            int seatId = Integer.parseInt(id);
            Seat seat = seatDAO.getSeatById(seatId);
            seats.add(seat);
        }
        return seats;
    }
}
