package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.StockRepository;
@Service
public class ServiceStockImpl implements IserviceStock{

	@Autowired
	StockRepository stockRepository;
	@Autowired
	EmailSenderService mailer;
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

	
	public boolean verifyStock(Long id) {
		Stock s = stockRepository.findById(id).orElse(null);
		return s.getQteMin() >= s.getQteStock();
		
	}

	@Override
	public void avertirStock(Long id) throws MessagingException {
		Stock s = stockRepository.findById(id).orElse(null);
		if(this.verifyStock(id)){
			List<String> fournisseurs= new ArrayList<>();
			for(Fournisseur f:s.getProduits().get(1).getFournisseurs()){
				fournisseurs.add(f.getLibelleFournisseur());
			}
			
			mailer.sendEmailWithAttachment("admin@admin.com", "<h1>Quantité minimale du stock atteinte"
					+ "produit: "+s.getProduits().get(1).getLibelle()+
					"stock: "+s.getLibelleStock()+
					"liste de fournisseurs: "+fournisseurs.toString()+"</h1>" ,"Stock de" +s.getProduits().get(1).getLibelle()+" presque épuisé");
		}
	}

}
