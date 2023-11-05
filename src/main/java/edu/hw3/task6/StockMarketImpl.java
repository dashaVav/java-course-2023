package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class StockMarketImpl implements StockMarket {
    PriorityQueue<Stock> queue;

    public StockMarketImpl() {
        queue = new PriorityQueue<>(Comparator.comparingDouble(Stock::getPrice).reversed());
    }

    @Override
    public void add(Stock stock) {
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.peek();
    }
}
