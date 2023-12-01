package edu.hw8.Task1.Server;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class ClientHandlerTest {

    private static Stream<String[]> testStream() {
        return Stream.of(
            new String[] {"личности", "Не переходи на личности там, где их нет"},
            new String[] {"оскарбления",
                "Eсли твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"},
            new String[] {"глупый",
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."},
            new String[] {"интеллект", "Чем ниже интеллект, тем громче оскорбления"},
            new String[] {"бэк", "Меняю классный код на вилку 200к...)"}
        );
    }

    @ParameterizedTest
    @DisplayName("run - Server responds to client")
    @MethodSource("testStream")
    void run_clientSendsMessage_serverRespondsCorrectly(String input, String expected) {
        TestSocket testSocket = new TestSocket(input);
        // Arrange
        ClientHandler clientHandler = new ClientHandler(testSocket);

        // Act
        clientHandler.run();

        // Assert
        String actualResponse = testSocket.getOutput();
        assertThat(actualResponse).isEqualTo(expected);
    }

}
