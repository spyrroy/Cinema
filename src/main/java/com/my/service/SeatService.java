package com.my.service;

import com.my.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getFreeSeatsBySessionId(int id);
    List<Seat> getSeatListFromArray(String[] ids);
}
