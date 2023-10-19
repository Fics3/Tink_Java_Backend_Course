package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) throws ConnectionException {
        if (random.nextBoolean()) {
            throw new ConnectionException("Failed to execute Command");
        }
        LOGGER.info("Faulty connection working | your command: " + command);
    }

    @Override
    public void close() {
        LOGGER.info("Faulty connection closed");
    }
}
