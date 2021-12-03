package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Facture;

public interface IserviceFacture {
	List<Facture> retrieveAllFactures();
	Facture cancelFacture(Long id);
	Facture retrieveFacture(Long id);
	Facture addfacture(Facture f , long idClient);
}
