package com.my.service;

import com.my.entity.Seat;
import com.my.entity.Session;
import com.my.entity.Ticket;
import com.my.entity.User;

import java.util.List;

public interface TicketService {
    void add(Ticket ticket);

    List<Ticket> createTicketList(User user, List<Seat> seats, Session session);

    void addAll(List<Ticket> tickets);
    List<Ticket> getTicketsByUserId(int id);
}
