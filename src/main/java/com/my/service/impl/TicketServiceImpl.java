package com.my.service.impl;

import com.my.DAO.TicketDAO;
import com.my.entity.Seat;
import com.my.entity.Session;
import com.my.entity.Ticket;
import com.my.entity.User;
import com.my.service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public void add(Ticket ticket) {
        ticketDAO.addTicket(ticket);
    }

    @Override
    public List<Ticket> createTicketList(User user, List<Seat> seats, Session session) {
        List<Ticket> tickets = new ArrayList<>();
        for (Seat seat : seats) {
            Ticket ticket = new Ticket(user, seat, session);
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public void addAll(List<Ticket> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            throw new RuntimeException("Received ticket list is null or empty");
        }
        for (Ticket ticket : tickets) {
            add(ticket);
        }
    }

    @Override
    public List<Ticket> getTicketsByUserId(int id) {
        List<Ticket> tickets = ticketDAO.getTicketsByUserId(id);
        return tickets;
    }
}
