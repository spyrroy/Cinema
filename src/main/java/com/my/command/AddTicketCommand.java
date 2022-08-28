package com.my.command;

import com.my.entity.Seat;
import com.my.entity.Session;
import com.my.entity.Ticket;
import com.my.entity.User;
import com.my.service.SeatService;
import com.my.service.SessionService;
import com.my.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AddTicketCommand extends Command {
    private final SeatService seatService;
    private final SessionService sessionService;
    private final TicketService ticketService;
    public AddTicketCommand(SeatService seatService, SessionService sessionService, TicketService ticketService) {
        this.seatService = seatService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String[] selectedSeats = req.getParameterValues("selectedSeats");
        User user = (User) req.getSession().getAttribute("user");
        int sessionId = Integer.parseInt(req.getParameter("sessionId"));
        Session session = sessionService.getSessionById(sessionId);
        List<Seat> seats = seatService.getSeatListFromArray(selectedSeats);

        List<Ticket> tickets = ticketService.createTicketList(user, seats, session);
        ticketService.addAll(tickets);
        return "redirect:app?cmd=allSessions";
    }
}
