package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	@Scheduled(fixedRate=600000)
	public void avertirStock() throws MessagingException {
		List<Stock> list =stockRepository.findAll();
		String body="Ces stocks ont atteint la limite\n";
		for(Stock s:list){
			if(this.verifyStock(s.getIdStock())&& s.getProduits().size()>0){
				List<String> fournisseurs= new ArrayList<>();
				body=body+"\n stock "+s.getLibelleStock()+" du produit "+s.getProduits().get(0).getLibelle()+
						" est en rouge\n   la quantité minimale: "+s.getQteMin()
				+" , la quantité actuelle: "+s.getQteStock()+" \n liste de fournisseurs: ";
				for(Fournisseur f:s.getProduits().get(0).getFournisseurs()){
					body = body+"\n         "+f.getLibelleFournisseur()+" numéro: "+f.getTelephone();
				}
				
				
			}
		}
		mailer.sendEmailWithAttachment("admin@admin.com", body ,"Rapport Stock");
		
	}

}
