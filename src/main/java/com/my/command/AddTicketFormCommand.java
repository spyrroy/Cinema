package com.my.command;

import com.my.entity.Seat;
import com.my.entity.Session;
import com.my.service.SeatService;
import com.my.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AddTicketFormCommand extends Command {
    private final SeatService seatService;
    private final SessionService sessionService;
    public AddTicketFormCommand(SeatService seatService, SessionService sessionService) {
        this.seatService = seatService;
        this.sessionService = sessionService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int sessionId = Integer.parseInt(req.getParameter("id"));
        Session session = sessionService.getSessionById(sessionId);
        req.setAttribute("session", session);
        List<Seat> freeSeats = seatService.getFreeSeatsBySessionId(sessionId);
        req.setAttribute("freeSeats", freeSeats);
        return "WEB-INF/session.jsp";
    }
}
