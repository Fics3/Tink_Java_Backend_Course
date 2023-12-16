package edu.hw8.Task1.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class QuoteServerTest {

    private QuoteServer quoteServer;

    @BeforeEach
    void setUp() {
        quoteServer = new QuoteServer(10000, 5);
    }

    @AfterEach
    void tearDown() {
        quoteServer.stopServer();
    }

    @Test
    @DisplayName("startServer - should call client handler")
    void startServer_AcceptsClient_CallsClientHandler() {
        //Arrange
        new Thread(() -> quoteServer.startServer()).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //Act
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try (Socket clientSocket = new Socket("localhost", 10000)) {
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Assert
        assertThat(latch.getCount()).isZero();
    }

    @Test
    @DisplayName("startServer - Clients more than pool")
    void startServer_AcceptClients_HandleClientsMoreThanPool() {
        //Arrange
        new Thread(() -> quoteServer.startServer()).start();

        int clientsCount = 10;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CountDownLatch latch = new CountDownLatch(clientsCount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientsCount);

        //Act
        for (int i = 0; i < clientsCount; i++) {
            executorService.submit(() -> {
                try (Socket clientSocket = new Socket("localhost", 10000)) {
                    Thread.sleep(100);
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Assert
        assertThat(latch.getCount()).isZero();
    }

}
