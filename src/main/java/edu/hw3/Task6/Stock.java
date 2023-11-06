package edu.hw3.Task6;

import java.util.Comparator;

public class Stock {
    private static final Comparator<Stock> PRICE_COMPARATOR = Comparator.comparingInt(Stock::getPrice).reversed();
    private final String name;
    private final int price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Comparator<Stock> getPriceComparator() {
        return PRICE_COMPARATOR;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
