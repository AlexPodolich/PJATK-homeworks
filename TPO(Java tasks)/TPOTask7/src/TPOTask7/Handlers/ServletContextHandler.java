package TPOTask7.Handlers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ServletContextHandler implements ServletContextListener {

    protected static final Logger LOGGER;
    private static final Random RANDOM;
    private static final List<Level> LEVELS;

    private static int CURRENT_LEVEL = 0;

    static {
        LOGGER = Logger.getLogger(ServletContextHandler.class.getName());
        RANDOM = new Random();
        LEVELS = Arrays.asList(Level.SEVERE, Level.OFF);
    }

    protected static void loggerLevel() {
        Level level = LEVELS.get(CURRENT_LEVEL);
        CURRENT_LEVEL = (CURRENT_LEVEL + 1) % LEVELS.size();
        LOGGER.setLevel(level);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) { //
        LOGGER.severe("CONTEXT INITIALIZED: " + event.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { //
        LOGGER.severe("CONTEXT DESTROYED: " + event.toString());
    }
}
