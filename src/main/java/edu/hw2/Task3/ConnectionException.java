package edu.hw2.Task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String s, Throwable cause) {
        super(s, cause);
    }

    public ConnectionException() {
    }

    public ConnectionException(String failedToExecuteCommand) {
        super(failedToExecuteCommand);
    }

}
