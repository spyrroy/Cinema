package com.my.listener;

import com.my.DAO.*;
import com.my.DAO.impl.*;
import com.my.command.*;
import com.my.service.*;
import com.my.service.impl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {
	private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

	// bootstrap of the application 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		jakarta.servlet.jsp.jstl.fmt.LocaleSupport l;
		LOG.debug("Start context initialization");

		ServletContext context = sce.getServletContext();

		initDatasource(context);
		LOG.debug("DataSource initialized");

		initServices(context);
		LOG.debug("Services initialized");
		

	}

	private void initDatasource(ServletContext context) throws IllegalStateException {
		String dataSourceName = context.getInitParameter("dataSource");
		LOG.trace("dataSourceName: {}", dataSourceName);
		Context jndiContext = null;
		try {
			jndiContext = (Context) new InitialContext().lookup("java:/comp/env");
			LOG.trace("jndiContext: {}", jndiContext);
			DataSource dataSource = (DataSource) jndiContext.lookup(dataSourceName); // "jdbc/mysql"
			LOG.trace("dataSource: {}", dataSource);
			context.setAttribute("dataSource", dataSource);
			LOG.trace("context.setAttribute 'dataSource': {}", dataSource.getClass().getName());
		} catch (NamingException e) {
			throw new IllegalStateException("Cannot initialize dataSource", e);
		}
	}


	private void initServices(ServletContext context) {
		// create Dao if it required
		DataSource ds = (DataSource) context.getAttribute("dataSource");
		FilmDAO filmDAO = new FilmDAOImpl(ds);
		LOG.trace("created 'filmDAO': {}", filmDAO);

		GenreDAO genreDAO = new GenreDAOImlp(ds);
		LOG.trace("created 'genreDAO': {}", genreDAO);

		SessionDAO sessionDAO = new SessionDAOImpl(ds);
		LOG.trace("created 'sessionDAO': {}", sessionDAO);

		UserDAO userDAO = new UserDAOImpl(ds);
		LOG.trace("created 'userDAO': {}", userDAO);

		SeatDAO seatDAO = new SeatDAOImpl(ds);
		LOG.trace("created 'seatDAO': {}", seatDAO);

		TicketDAO ticketDAO = new TicketDAOImpl(ds);
		LOG.trace("created 'ticketDAO': {}", ticketDAO);
//
//		// create services
		FilmService filmService = new FilmServiceImpl(filmDAO);
		context.setAttribute("filmService", filmService);
		LOG.trace("context.setAttribute 'filmService': {}", filmService);

		GenreService genreService = new GenreServiceImpl(genreDAO);
		context.setAttribute("genreService", genreService);
		LOG.trace("context.setAttribute 'genreService': {}", genreService);

		SessionService sessionService = new SessionServiceImpl(sessionDAO);
		context.setAttribute("sessionService", sessionService);
		LOG.trace("context.setAttribute 'sessionService': {}", sessionService);

		UserService userService = new UserServiceImpl(userDAO);
		context.setAttribute("userService", userService);
		LOG.trace("context.setAttribute 'userService': {}", userService);

		SeatService seatService = new SeatServiceImpl(seatDAO);
		context.setAttribute("seatService", seatService);
		LOG.trace("context.setAttribute 'seatService': {}", seatService);

		TicketService ticketService = new TicketServiceImpl(ticketDAO);
		context.setAttribute("ticketService", ticketService);
		LOG.trace("context.setAttribute 'ticketService': {}", ticketService);

//
		CommandContainer commands = new CommandContainer();
		Command command = new IndexCommand();
		commands.addCommand(null, command);
		commands.addCommand("", command);
//
//		// add product flow
		command = new AddFilmFormCommand(filmService);
		commands.addCommand("addFilmForm", command);
		command = new AddFilmCommand(filmService, genreService);
		commands.addCommand("addFilm", command);
		command = new AllFilmsCommand(filmService);
		commands.addCommand("allFilms", command);
		command = new DeleteFilmCommand(filmService);
		commands.addCommand("deleteFilm", command);
		command = new EditFilmCommand(filmService, genreService);
		commands.addCommand("editFilm", command);
		command = new AddSessionFormCommand(sessionService, filmService);
		commands.addCommand("addSessionForm", command);
		command = new AddSessionCalendarFormCommand(sessionService, filmService);
		commands.addCommand("addSessionCalendarForm", command);
		command = new AddSessionDateFormCommand(sessionService, filmService);
		commands.addCommand("addSessionDateForm", command);
		command = new AddSessionCommand(sessionService, filmService);
		commands.addCommand("addSession", command);
		command = new AllSessionsCommand(sessionService, filmService);
		commands.addCommand("allSessions", command);
		command = new DeleteSessionCommand(sessionService);
		commands.addCommand("deleteSession", command);
		command = new LoginPageCommand();
		commands.addCommand("loginPage", command);
		command = new LoginCommand(userService);
		commands.addCommand("login", command);
		command = new RegisterPageCommand();
		commands.addCommand("registerPage", command);
		command = new RegisterCommand(userService);
		commands.addCommand("register", command);
		command = new AddTicketFormCommand(seatService, sessionService);
		commands.addCommand("addTicketForm", command);
		command = new AddTicketCommand(seatService, sessionService, ticketService);
		commands.addCommand("addTicket", command);
		command = new AllTicketsCommand(sessionService, filmService, ticketService);
		commands.addCommand("allTickets", command);
//
		context.setAttribute("commandContainer", commands);
		LOG.trace("context.setAttribute 'commandContainer': {}", commands);
	}
}
