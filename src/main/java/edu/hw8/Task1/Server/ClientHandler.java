package edu.hw8.Task1.Server;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientHandler extends Thread implements Runnable {

    private static final int BYTE_BUFFER_SIZE = 1024;

    private static final Map<String, String> RESPONSES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскарбления", "Eсли твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления",
        "бэк", "Меняю классный код на вилку 200к...)"
    );
    private final Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
            int bytesRead;
            while ((bytesRead = clientSocket.getInputStream().read(byteBuffer.array())) > 0) {
                String userInput = new String(
                    byteBuffer.array(),
                    0,
                    bytesRead,
                    StandardCharsets.UTF_8
                );
                log.info("Клиент прислал: " + userInput);

                sendMessageBack(userInput, byteBuffer);
            }
        } catch (IOException e) {
            log.error("Ошибка!");
            throw new RuntimeException(e);
        }
    }

    private void sendMessageBack(String userInput, ByteBuffer buffer) {
        try {
            String response = getResponse(userInput);
            buffer.clear();
            buffer.put(response.getBytes());
            buffer.flip();
            clientSocket.getOutputStream().write(buffer.array(), 0, buffer.limit());
            clientSocket.getOutputStream().flush();
            buffer.clear();
        } catch (IOException e) {
            log.error("Ошибка во время отправки сообщения");
            throw new RuntimeException(e);
        }
    }

    private String getResponse(String input) {
        for (var entry : RESPONSES.entrySet()) {
            if (input.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Бебебе я бэкэндер в компании тинькофф бебебе!)";
    }
}
