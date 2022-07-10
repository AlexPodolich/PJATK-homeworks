package TPOTask7.Handlers;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ServletRequestHandler implements ServletRequestListener {
    private static final Logger LOGGER;
    private static final Random RANDOM;
    private static final List<Level> LEVELS;

    private static int CURRENT_LEVEL = 0;

    static {
        LOGGER = Logger.getLogger(ServletRequestHandler.class.getName());
        RANDOM = new Random();
        LEVELS = Arrays.asList(Level.SEVERE, Level.OFF);
    }

    private static void loggerLevel() {
        Level level = LEVELS.get(CURRENT_LEVEL);
        CURRENT_LEVEL = (CURRENT_LEVEL + 1) % LEVELS.size();
        LOGGER.setLevel(level);
    }

    public void requestInitialized(ServletRequestEvent ev) {
        loggerLevel();
        LOGGER.severe("REQUEST INITIALIZED: " + ev.toString());
    }

    public void requestDestroyed(ServletRequestEvent ev) { //
        LOGGER.severe("REQUEST DESTROYED: " + ev.toString());
    }
}
