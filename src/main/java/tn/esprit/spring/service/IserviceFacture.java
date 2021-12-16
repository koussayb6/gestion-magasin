package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;

public interface IserviceFacture {
	List<Facture> retrieveAllFactures();
	Facture cancelFacture(Long id);
	Facture retrieveFacture(Long id);
	Facture addfacture(Facture f, Long idClient, String codePromo);
	List<Facture> getFacturesClient(Long idClient);
	List<Facture> getProduitFacture(Long idProduit);
	List<Facture> getCategorieFacture(CategorieProduit cat);
	List<Facture> getFactures(Float prixmin , Float prixmax , Date datedebut , Date datefin);

}
