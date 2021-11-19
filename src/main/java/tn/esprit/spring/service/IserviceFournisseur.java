package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Fournisseur;

public interface IserviceFournisseur  {
	List<Fournisseur> retrieveAllFournissuers();

	Fournisseur addFournisseur(Fournisseur f);

	void deleteFournissur(Long id);

	Fournisseur updateFournisseur(Fournisseur f);

	Fournisseur retrieveFournisseur(Long id);
}
