package io.buchin.controllers.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Created by fedinskiy on 24.02.17.
 */
public class ApplicationLoadListener implements ServletContextListener {
	Logger logger = Logger.getLogger(ApplicationLoadListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.trace("site started");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.trace("site stoped");
	}
}
