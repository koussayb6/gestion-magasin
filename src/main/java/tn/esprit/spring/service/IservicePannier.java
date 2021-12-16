package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.PanierStatus;

public interface IservicePannier {
	
	List<Panier> retrieveByClinet(Long idClient);

	Panier addPanier(Long idClient , Long idProduit , int qte);

	void changeStatus(Panier p,PanierStatus ps);

	

}
