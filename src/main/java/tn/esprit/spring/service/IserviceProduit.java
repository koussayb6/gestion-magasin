package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Produit;

public interface IserviceProduit {
	List<Produit> retrieveAllProduits(Float minPrix, Float maxPrix, String libelle, org.springframework.data.domain.Pageable pageable) ;

	Produit addProduit(Produit p, Long idRayon, Long idStock, CategorieProduit cat);

	Produit retrieveProduit(Long id);
	
	void affectProduitToStock(Long idProduit, Long idStock) ;
	void affectProduitToFournisseur(Long idProduit, Long idFourn);
	float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);
	float getRevenuBrutCategorieProduit(CategorieProduit cat, Date startDate, Date endDate);


}
