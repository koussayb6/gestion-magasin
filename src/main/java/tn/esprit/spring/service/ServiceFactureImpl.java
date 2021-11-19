package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.FactureRepository;

public class ServiceFactureImpl implements IserviceFacture {

	@Autowired
	FactureRepository factureRepository;
	@Override
	public List<Facture> retrieveAllFactures() {
		return factureRepository.findAll();
	}

	@Override
	public Facture cancelFacture(Long id) {
		Facture facture= factureRepository.findById(id).orElse(null);
		facture.setActive(false);
		factureRepository.save(facture);
		return facture;
	}

	@Override
	public Facture retrieveFacture(Long id) {
		return factureRepository.findById(id).orElse(null);
	}

}
