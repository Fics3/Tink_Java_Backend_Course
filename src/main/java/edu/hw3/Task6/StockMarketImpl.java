package edu.hw3.Task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    PriorityQueue<Stock> marketQueue;

    public StockMarketImpl() {
        marketQueue = new PriorityQueue<>();
    }

    @Override
    public void add(Stock stock) {
        marketQueue.offer(stock);
    }

    @Override
    public void remove(Stock stock) {
        marketQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return marketQueue.peek();
    }

    public int getSize() {
        return marketQueue.size();
    }

}
