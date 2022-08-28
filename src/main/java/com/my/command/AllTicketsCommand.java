package com.my.command;

import com.my.entity.Session;
import com.my.entity.Ticket;
import com.my.entity.User;
import com.my.service.FilmService;
import com.my.service.SessionService;
import com.my.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AllTicketsCommand extends Command {
    private static final Logger LOG = LoggerFactory.getLogger(AllTicketsCommand.class);
    private final SessionService sessionService;
    private final FilmService filmService;
    private final TicketService ticketService;
    public AllTicketsCommand(SessionService sessionService, FilmService filmService, TicketService ticketService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.ticketService = ticketService;
    }

    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
        req.setAttribute("tickets", tickets);
        return "WEB-INF/allTickets.jsp";
    }
}
