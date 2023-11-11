package edu.hw3.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockTest {

    @Test
    @DisplayName("Test for equals stocks")
    void compareTo_shouldReturnZeroWhenStocksEquals() {
        // Arrange
        Stock stock1 = new Stock("Adidas", 100);
        Stock stock2 = new Stock("Puma", 100);

        // Act
        int result = Stock.getPriceComparator().compare(stock1, stock2);

        // Assert
        assertThat(result).isEqualTo(0);
        // 100 = 100 -> 0
    }

    @Test
    @DisplayName("Test for first stock bigger than second")
    void compareTo_shouldReturnMinusOneWhenFirstBigger() {
        // Arrange
        Stock stock1 = new Stock("Adidas", 150);
        Stock stock2 = new Stock("Puma", 100);

        // Act
        int result = Stock.getPriceComparator().compare(stock1, stock2);

        // Assert
        assertThat(result).isEqualTo(-1);
        // 150 > 100 -> -1
    }

    @Test
    @DisplayName("Test for first stock bigger than second")
    void compareTo_shouldReturnOneWhenSecondBigger() {
        // Arrange
        Stock stock1 = new Stock("Adidas", 100);
        Stock stock2 = new Stock("Puma", 150);

        // Act
        int result = Stock.getPriceComparator().compare(stock1, stock2);

        // Assert
        assertThat(result).isEqualTo(1);
        // 100 < 150 -> 1
    }
}
