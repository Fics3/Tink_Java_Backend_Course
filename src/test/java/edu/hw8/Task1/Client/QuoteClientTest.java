package edu.hw8.Task1.Client;

import java.io.IOException;
import java.net.ServerSocket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

public class QuoteClientTest {

    @Test
    @DisplayName("connect - Connect to the server")
    void connect_ConnectToTheServer_ShouldNotThrowException() {
        //Arrange
        assertThatCode(() -> {
            ServerSocket serverSocket = new ServerSocket(10000);
            QuoteClient quoteClient = new QuoteClient("localhost", 10000);
            Thread threadServer = new Thread(() -> {
                try {
                    serverSocket.accept();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread threadClient = new Thread(quoteClient::connect);

            //Act
            threadServer.start();
            threadClient.start();

            Thread.sleep(1000);

            serverSocket.close();
            quoteClient.getSocket().close();
            threadServer.interrupt();
            threadClient.interrupt();

            //Assert
        }).doesNotThrowAnyException();
    }

}
