package edu.hw8.Task1.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class QuoteClient {
    private static final int BYTE_BUFFER_SIZE = 1024;
    private final Socket socket;

    public QuoteClient(String ip, int port) {
        try {
            socket = new Socket(InetAddress.getByName(ip), port);
        } catch (IOException e) {
            log.error("No server on this port: " + port);
            throw new RuntimeException(e);
        }
    }

    public void connect() {
        Thread thread = new Thread(() -> receiveMessage(socket));
        thread.start();
        sendMessage(socket);
    }

    private void sendMessage(Socket socket) {
        try {
            Scanner scanner = new Scanner(System.in);
            ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
            while (!socket.isClosed()) {
                String line = scanner.nextLine();

                useIfCommand(line);

                byteBuffer.put(line.getBytes(StandardCharsets.UTF_8));
                byteBuffer.flip();

                socket.getOutputStream().write(byteBuffer.array(), 0, byteBuffer.limit());
                byteBuffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void receiveMessage(Socket socket) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
        try {
            int bytesRead;
            while ((bytesRead = socket.getInputStream().read(byteBuffer.array())) != -1) {
                String fromServer = new String(
                    byteBuffer.array(),
                    0,
                    bytesRead,
                    StandardCharsets.UTF_8
                );
                log.info(fromServer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            log.error("Сообщение не отправлено", e);
        }
    }

    private void useIfCommand(String input) {
        if (input.startsWith("@")) {
            try {
                if (input.startsWith("@quit ")) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
