package tn.esprit.spring.batch;

import org.springframework.batch.item.ItemProcessor;

import tn.esprit.spring.entity.Stock;

public class StockProcessor implements ItemProcessor<Stock, Stock> {
	/*11. logique métier de notre job*/
	@Override
	public Stock process(Stock stock) {
		
		stock.setLibelleStock(stock.getLibelleStock().toUpperCase());
		stock.setQteStock(stock.getQteStock());
		stock.setQteMin(stock.getQteMin());
		return stock;
	}
}

