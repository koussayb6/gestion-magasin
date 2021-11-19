package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.service.IserviceStock;

@RestController
public class StockRestController {
	
	@Autowired
	IserviceStock servicestock;
	
	
	@GetMapping("/sotcks")
	public List<Stock> getAllstocks(){
		return servicestock.retrieveAllStocks();
	}

	
	@PostMapping("/addstock")
	@ResponseBody
	public Stock addStock(@RequestBody Stock s){
		
		return servicestock.addStock(s);
	}
	
	
	@DeleteMapping("/deletestock/{idStock}")
	@ResponseBody
	public void deleteStock (@PathVariable ("idStock") Long idStock ){
		servicestock.deleteStock(idStock);
	}
	
	@PutMapping
	@ResponseBody
	public Stock updateStock(@RequestBody Stock s){
		return servicestock.updateStock(s);

	}
}
