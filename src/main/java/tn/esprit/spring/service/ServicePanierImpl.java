package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.PanierStatus;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.PannierRepository;
import tn.esprit.spring.repository.ProduitRepository;

@Service
public class ServicePanierImpl implements IservicePannier {
	
	@Autowired
	PannierRepository prepo;

	@Autowired
	ClientRepository crepo;
	
	@Autowired
	ProduitRepository pr;
	
	
	@Override
	public List<Panier> retrieveByClinet(Long idClient) {
		Client c = crepo.findById(idClient).orElse(null);
		
		return null ;
	}

	@Override
	public Panier addPanier(Long idClient, Long idProduit, int qte) {
		Client c = crepo.findById(idClient).orElse(null);
		Produit p = pr.findById(idProduit).orElse(null);
		Panier panier = new Panier();
		//panier.setClient(c);
		//panier.setProduit(p);
		panier.setQuantite(qte);
		panier.setPanierStatus(PanierStatus.PANIER);
		prepo.save(panier);
		
		return panier;
	}

	@Override
	public void changeStatus(Panier p ,PanierStatus ps) {
		p.setPanierStatus(ps);
		prepo.save(p);
		
	}

}
