package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double DEFAULT_CHANCE_OF_FAULTY = 50;
    private static final double MAX_CHANCE_OF_FAULTY = 100;
    private final double currentChanceOfFaulty;

    public DefaultConnectionManager(double currentChanceOfFaulty) {
        if (currentChanceOfFaulty > MAX_CHANCE_OF_FAULTY || currentChanceOfFaulty < 0) {
            this.currentChanceOfFaulty = MAX_CHANCE_OF_FAULTY;
        } else {
            this.currentChanceOfFaulty = currentChanceOfFaulty;
        }
    }

    public DefaultConnectionManager() {
        currentChanceOfFaulty = DEFAULT_CHANCE_OF_FAULTY;
    }

    @Override
    public Connection getConnection() {

        Random random = new Random();
        Connection res;

        if (random.nextDouble(MAX_CHANCE_OF_FAULTY) >= currentChanceOfFaulty) {
            res = new StableConnection();
        } else {
            res = new FaultyConnection();
        }

        return res;
    }
}
