package com.csci5308.stocki5.trade.sell;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.StockDb;
import com.csci5308.stocki5.stock.db.StockDbHighestLowest;
import com.csci5308.stocki5.stock.fetch.IStockFetch;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeDb;
import com.csci5308.stocki5.trade.order.ITradeOrder;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class TradeSellController
{
	@Autowired
	ITradeOrder iTradeOrder;

	@Autowired
	IStockFetch iStockFetch;

	@Autowired
	ITradeSell iTradeSell;
	
	@Autowired
	StockDbHighestLowest stockDbHighestLowest;

	@Autowired
	StockDb stockDb;

	@Autowired
	UserDb userDb;

	@Autowired
	TradeDb tradeDb;

	@RequestMapping(value = "/sellstock", method = RequestMethod.POST)
	public ModelAndView sellStock(HttpServletRequest request, @RequestParam(value = "sellstockid") int stockId,
			@RequestParam(value = "quantity") int quantity,
			@RequestParam(value = "tradeBuyNumber") String tradeBuyNumber)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isSold = iTradeSell.sellStock(principal.getName(), stockId, quantity, stockDb, userDb, tradeDb,
				tradeBuyNumber);
		if (isSold)
		{
			List<Trade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject("orders", orders);
			model.setViewName("orders");
			return model;
		}

		List<Stock> stocks = iStockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> top5GainersStocks = iStockFetch.fetchTop5GainerStocks(stockDbHighestLowest, userDb, principal.getName());
		List<Stock> top5LosersStocks = iStockFetch.fetchTop5LoserStocks(stockDbHighestLowest, userDb, principal.getName());

		model.addObject("stocks", stocks);
		model.addObject("gainers", top5GainersStocks);
		model.addObject("losers", top5LosersStocks);
		model.addObject("error", "Insufficient funds");
		model.setViewName("stocks");
		return model;
	}

	@RequestMapping(value = "/setsellstock", method = RequestMethod.POST)
	public ModelAndView setBwllStock(HttpServletRequest request, @RequestParam(value = "setsellstockid") int stockId,
			@RequestParam(value = "setquantity") int quantity, @RequestParam(value = "setsellprice") float sellPrice,
			@RequestParam(value = "tradeBuyNumber") String tradeBuyNumber)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isSold = iTradeSell.setSellPrice(principal.getName(), stockId, quantity, sellPrice, stockDb, userDb,
				tradeDb, tradeBuyNumber);
		if (isSold)
		{
			List<Trade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject("orders", orders);
			model.setViewName("orders");
			return model;
		}

		List<Stock> stocks = iStockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> top5GainersStocks = iStockFetch.fetchTop5GainerStocks(stockDbHighestLowest, userDb, principal.getName());
		List<Stock> top5LosersStocks = iStockFetch.fetchTop5LoserStocks(stockDbHighestLowest, userDb, principal.getName());

		model.addObject("stocks", stocks);
		model.addObject("gainers", top5GainersStocks);
		model.addObject("losers", top5LosersStocks);
		model.addObject("error", "Insufficient funds");
		model.setViewName("stocks");
		return model;
	}
}
