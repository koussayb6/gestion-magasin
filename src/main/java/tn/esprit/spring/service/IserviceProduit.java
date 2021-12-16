package tn.esprit.spring.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Produit;

public interface IserviceProduit {
	List<Produit> retrieveAllProduits(Float minPrix, Float maxPrix, String libelle, org.springframework.data.domain.Pageable pageable) ;

	Produit addProduit(Produit p, Long idRayon, Long idStock, CategorieProduit cat, MultipartFile file) throws IOException;
	Produit updateProduit(Long idProduit,Produit p, Long idRayon, Long idStock, CategorieProduit cat, MultipartFile file) throws IOException;
	Produit getProduit(Long id);
	Produit retrieveProduit(Long id);
	List<Produit>  pic(Long id);
	void deleteProduit(Long id);
	void affectProduitToStock(Long idProduit, Long idStock) ;
	void affectProduitToFournisseur(Long idProduit, Long idFourn);
	float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);
	float getRevenuBrutCategorieProduit(CategorieProduit cat, Date startDate, Date endDate);
	byte[] decompressBytes(byte[] data);



}
