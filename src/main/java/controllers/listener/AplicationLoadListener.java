package controllers.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by yuri on 24.02.17.
 */
public class AplicationLoadListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(AplicationLoadListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.trace("Webapp start");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("Webapp stop");
    }
}
