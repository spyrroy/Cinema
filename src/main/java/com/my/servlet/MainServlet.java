package servlet;

import command.Command;
import command.CommandContainer;
import command.CommandException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/app")
public class MainServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MainServlet.class);

    private CommandContainer commands;

    @Override
    public void init(ServletConfig config) throws ServletException {
        commands = (CommandContainer) config.getServletContext().getAttribute("commandContainer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cmdName = req.getParameter("cmd");
            LOG.debug("cmdName: {} ", cmdName);
            Command command = commands.getCommand(cmdName);
            LOG.debug("cmdName: {} ", command);
            String url = null;

            url = command.doCommand(req, resp);

            LOG.debug("url: {} ", url);
            if (url.startsWith("redirect:")) {
                resp.sendRedirect(url.substring("redirect:".length()));
                return;
            }
            req.getRequestDispatcher(url).forward(req, resp);
        } catch (CommandException e) {
            throw new ServletException("Cannot process the command", e);
//            resp.sendError(500, "Cannot process the command");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
