package edu.hw3.Task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockMarketImplTest {

    StockMarketImpl stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockMarketImpl(Stock.getPriceComparator());
    }

    @Test
    @DisplayName("Test for add stock to market")
    void add_afterAddMarketSizeShouldBeNotZero() {
        // Arrange
        Stock stock = new Stock("Adidas", 100);

        // Act
        stockMarket.add(stock);

        // Assert
        assertThat(stockMarket.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test for remove stock from market")
    void remove_afterRemoveMarketSizeShouldBeZero() {
        // Arrange
        Stock stock = new Stock("Adidas", 2500);
        stockMarket.add(stock);

        // Act
        stockMarket.remove(stock);

        // Assert
        assertThat(stockMarket.getSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test for most valuable stock")
    void calculateTotalMarketValue_shouldReturnStockWithHighestPrice() {
        // Arrange
        Stock stock1 = new Stock("Adidas", 150);
        Stock stock2 = new Stock("puma", 2500);
        stockMarket.add(stock1);
        stockMarket.add(stock2);

        // Act
        Stock mostValuableStock = stockMarket.mostValuableStock();

        // Assert
        assertThat(mostValuableStock.price()).isEqualTo(2500);
    }
}
