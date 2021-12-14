package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.StockRepository;
@Service
public class ServiceStockImpl implements IserviceStock{

	@Autowired
	StockRepository stockRepository;
	@CrossOrigin("*")
	@Override
	public List<Stock> retrieveAllStocks() {
		List<Stock> list= (List<Stock>) stockRepository.findAll();
		return list;
	}

	@Override
	public Stock addStock(Stock s) {
		Stock st = stockRepository.save(s);
		return st;
	}

	@Override
	public Stock updateStock(Stock u) {
		return stockRepository.save(u);
	}

	@Override
	public Stock retrieveStock(Long id) {
		return stockRepository.findById(id).orElse(null) ;
	}
	
	@Override
	public void deleteStock(Long id) {
		stockRepository.deleteById(id);
	}

}
