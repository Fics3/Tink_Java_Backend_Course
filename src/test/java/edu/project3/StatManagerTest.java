package edu.project3;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StatManagerTest {

    List<NginxLog> nginxLogs = Arrays.asList(
        new NginxLog(
            "GET /page1 HTTP/1.1",
            200,
            120,
            OffsetDateTime.parse("2022-11-10T10:30:45+03:00"),
            "192.168.0.1"
        ),
        new NginxLog(
            "GET /page2 HTTP/1.1",
            404,
            50,
            OffsetDateTime.parse("2022-11-10T13:45:20+03:00"),
            "192.168.0.3"
        ),
        new NginxLog(
            "POST /page1 HTTP/1.1",
            200,
            80,
            OffsetDateTime.parse("2022-11-10T15:10:30+03:00"),
            "192.168.0.2"
        ),
        new NginxLog(
            "GET /page1 HTTP/1.1",
            500,
            150,
            OffsetDateTime.parse("2022-11-10T18:20:15+03:00"),
            "192.168.0.1"
        )
    );

    @Test
    @DisplayName("Count Resources")
    void countResources_shouldReturnResourceCounts() {
        // Act
        Map<String, Integer> result = StatManager.countResources(nginxLogs);

        // Assert
        assertThat(result).isNotNull().hasSize(3)
            .containsEntry("GET /page1 HTTP/1.1", 2)
            .containsEntry("GET /page2 HTTP/1.1", 1)
            .containsEntry("POST /page1 HTTP/1.1", 1);
    }

    @Test
    @DisplayName("Count Responses")
    void countResponses_shouldReturnResponseCounts() {
        // Act
        Map<Integer, Integer> result = StatManager.countResponses(nginxLogs);

        // Assert
        assertThat(result).isNotNull().hasSize(3)
            .containsEntry(200, 2)
            .containsEntry(404, 1)
            .containsEntry(500, 1);
    }

    @Test
    @DisplayName("Calculate Average Response Size")
    void calculateAverageResponseSize_shouldReturnAverageSize() {
        // Act
        int result = StatManager.calculateAverageResponseSize(nginxLogs);

        // Assert
        assertThat(result).isEqualTo((120 + 50 + 80 + 150) / 4);
    }

    @Test
    @DisplayName("Count Requests By Time Of Day")
    void countRequestsByTimeOfDay_shouldReturnRequestCountsByHour() {
        // Act
        Map<Integer, Integer> result = StatManager.countRequestsByTimeOfDay(nginxLogs);

        // Assert
        assertThat(result).isNotNull().hasSize(4)
            .containsEntry(10, 1)
            .containsEntry(13, 1)
            .containsEntry(15, 1)
            .containsEntry(18, 1);
    }

    @Test
    @DisplayName("Count Unique IP Addresses")
    void countUniqueIPAddresses_shouldReturnUniqueIPAddressCount() {
        // Act
        int uniqueIPCount = StatManager.countUniqueIPAddresses(nginxLogs);

        // Assert
        assertThat(uniqueIPCount).isEqualTo(3);
    }
}
