package com.csci5308.stocki5.trade.order;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class TradeOrderTest {

    private ITradeDb tradeDb = null;
    private ITradeOrder tradeOrder = null;

    @Before
    public void createObjects() {
        tradeDb = new TradeDbMock();
        tradeOrder = new TradeOrder();
    }

    @After
    public void destroyObjects() {
        tradeDb = null;
        tradeOrder = null;
    }

    @Test
    public void fetchUserOrdersTest() {
        List<Trade> userOrders = tradeOrder.fetchUserOrders("AB123456", tradeDb);
        Iterator<Trade> userOrdersInterator = userOrders.iterator();
        while (userOrdersInterator.hasNext()){
            Trade trade = userOrdersInterator.next();
            Assert.assertTrue(trade.generateTradeNumber());
            Assert.assertNotNull(trade.getTradeNumber());
            Assert.assertNotEquals(0, trade.getStockId());
            Assert.assertNotNull(trade.getBuySell());
            Assert.assertNotEquals(0, trade.getQuantity());
            Assert.assertNotNull(trade.getStatus());
            Assert.assertNotNull(trade.getStockDbInterface());
            Assert.assertNotNull(trade.getUserDbInterface());
        }
    }

    @Test
    public void fetchUserOrdersInvalidUserCodeTest() {
        List<Trade> userOrders = tradeOrder.fetchUserOrders("AB1234", tradeDb);
        int orderListSize = userOrders.size();
        Assert.assertEquals(0, orderListSize);
    }
}