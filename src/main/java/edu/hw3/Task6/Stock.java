package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final int price;
    private final String name;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(@NotNull Stock stock) {
        return Integer.compare(stock.price, this.price);
    }
}
