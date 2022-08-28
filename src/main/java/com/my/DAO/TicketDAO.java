package com.my.DAO;

import com.my.entity.Ticket;

import java.util.List;

public interface TicketDAO {
    boolean addTicket(Ticket ticket);
    List<Ticket> getTicketsByUserId(int id);
}
