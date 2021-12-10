package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;

public interface IserviceFacture {
	List<Facture> retrieveAllFactures();
	Facture cancelFacture(Long id);
	Facture retrieveFacture(Long id);
	Facture addfacture(Facture f, long idClient, String codePromo);
	List<Facture> getFacturesClient(Long idClient);

}
