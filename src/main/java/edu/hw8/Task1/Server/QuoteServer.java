package edu.hw8.Task1.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class QuoteServer {

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;

    public QuoteServer(int port, int threadPoolSize) {
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            log.error("Порт: " + port + " занят");
            throw new RuntimeException();
        }
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                executorService.submit(clientHandler);
            }
        } catch (IOException e) {
            log.info("Сокерт сервера закрыт");
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error("Ошибка во время закрытия сервера");
        }
    }
}
