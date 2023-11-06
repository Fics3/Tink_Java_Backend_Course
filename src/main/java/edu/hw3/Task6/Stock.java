package edu.hw3.Task6;

import java.util.Comparator;

public record Stock(String name, int price) {
    private static final Comparator<Stock> PRICE_COMPARATOR = Comparator.comparingInt(Stock::price).reversed();

    public static Comparator<Stock> getPriceComparator() {
        return PRICE_COMPARATOR;
    }
}
