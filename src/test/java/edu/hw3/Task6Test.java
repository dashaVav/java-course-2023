package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task6Test {
    StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockMarketImpl();
        Stock stock1 = new Stock("Apple", 100.0);
        Stock stock2 = new Stock("Google", 2000.0);
        Stock stock3 = new Stock("VK", 100000.0);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
    }

    @Test
    void testStockMarketAdd() {
        Stock stock = new Stock("Yandex", 10000000);

        stockMarket.add(stock);

        assertEquals(stock, stockMarket.mostValuableStock());
    }

    @Test
    void testStockMarketRemove() {
        Stock removeStock = new Stock("VK", 100000.0);
        Stock mostPopularStock = new Stock("Google", 2000.0);

        stockMarket.remove(removeStock);

        assertEquals(mostPopularStock, stockMarket.mostValuableStock());
    }

    @Test
    void testStockMarketValuableStock() {
        Stock removeStock = new Stock("VK", 100000.0);

        assertEquals(removeStock, stockMarket.mostValuableStock());
    }

    @Test
    public void testEmptyMarket() {
        StockMarket stockMarketNew = new StockMarketImpl();

        Stock mostValuable = stockMarketNew.mostValuableStock();

        assertNull(mostValuable);
    }
}
