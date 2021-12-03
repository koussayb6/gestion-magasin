package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Produit;

public interface IserviceProduit {
	List<Produit> retrieveAllProduits();

	Produit addProduit(Produit p, Long idRayon, Long idStock);

	Produit retrieveProduit(Long id);
	
	void affectProduitToStock(Long idProduit, Long idStock) ;
	void affectProduitToFournisseur(Long idProduit, Long idFourn);

}
