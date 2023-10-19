package edu.hw2.Task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager connectionManager, int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.manager = connectionManager;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) throws Exception {
        ConnectionException lastException = null;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return; // if succeed leave from func
            } catch (ConnectionException e) {
                lastException = e;
            }
        }
        throw new ConnectionException("Failed to execute command after " + maxAttempts + " attempts", lastException);
    }

}
