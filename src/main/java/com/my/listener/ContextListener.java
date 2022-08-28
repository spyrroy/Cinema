package apps.web.listener;

import apps.dao.ProductDao;
import apps.dao.mysql.ProductDaoImpl;
import apps.web.command.*;
import apps.web.service.ProductService;
import apps.web.service.ProductServiceImpl;
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
		
		String realPath = context.getRealPath("WEB-INF/classes/messages_en.properties");
		LOG.info(realPath);
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
		ProductDao productDao = new ProductDaoImpl((DataSource) context.getAttribute("dataSource"));
		LOG.trace("created 'productDao': {}", productDao);

		// create services
		ProductService productService = new ProductServiceImpl(productDao);
		context.setAttribute("addService", productService);
		LOG.trace("context.setAttribute 'addService': {}", productService);
		
		CommandContainer commands = new CommandContainer();
		Command command = new IndexCommand();
		commands.addCommand(null, command);
		commands.addCommand("", command);

		// add product flow
		command = new AddProductFormCommand();
		commands.addCommand("addProductForm", command);
		command = new AddProductCommand(productService);
		commands.addCommand("addProduct", command);
		command = new AllProductsCommand(productService);
		commands.addCommand("allProducts", command);

		context.setAttribute("commandContainer", commands);
		LOG.trace("context.setAttribute 'commandContainer': {}", commands);
	}
}
