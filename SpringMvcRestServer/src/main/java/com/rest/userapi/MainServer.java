package com.rest.userapi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.rest.userapi.config.MvcConfig;

public class MainServer {
	private static Logger log = LoggerFactory.getLogger(MainServer.class);

	public static void main(String[] args) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(MvcConfig.class);
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(9898);
		server.addConnector(connector);
		ServletContextHandler handler = new ServletContextHandler();
		DispatcherServlet frontController = new DispatcherServlet(context);
		frontController.setThrowExceptionIfNoHandlerFound(true);
		handler.addServlet(new ServletHolder(frontController), "/userapi/*");
		/*handler.addFilter((new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain"))), "/*",
				EnumSet.allOf(DispatcherType.class));*/

		server.setHandler(handler);
		try {
			server.start();
			log.info("########################server is running  on port 9898 #############################");
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
