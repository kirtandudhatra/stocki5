package com.csci5308.stocki5.stock.history;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.observer.IObserverMock;

public class StockMaintainHistoryObserverMockTest
{
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	IObserverMock stockMaintainHistoryObserverMock = null;

	@Before
	public void createObjects()
	{
		stockMaintainHistoryObserverMock = stockFactoryMock.createStockMaintainHistoryObserverMock();
	}

	@After
	public void destroyObjects()
	{
		stockMaintainHistoryObserverMock = null;
	}

	@Test
	public void updateTest()
	{
		Assert.assertEquals(true, stockMaintainHistoryObserverMock.update());
	}
}
