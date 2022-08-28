package com.my.DAO;

import com.my.entity.Seat;

import java.util.List;

public interface SeatDAO {
    List<Seat> getFreeSeatsBySessionId(int id);
    Seat getSeatById(int id);
}
