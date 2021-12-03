package tn.esprit.spring.service;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.ProduitRepository;
@Service
public class ServiceFactureImpl implements IserviceFacture {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	DetailFactureRepository dfactureRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	ClientRepository clientRepository;
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
	@Override
	public Facture addfacture(Facture f, long idClient) {
		Client c = clientRepository.findById(idClient).orElse(null);
		f.setClient(c);
		f.setDateFacture(new Date());
		float montant=0;
		for(DetailFacture df: f.getDetailFactures() )
		{
			montant+=df.getPrixTotal();
		}
		f.setMontantFacture(montant);
		Facture ff = factureRepository.save(f);

		return ff;
	}

	/*@Override
	public DetailFacture addfacture(Facture f, long idClient) {
		Client c = clientRepository.findById(idClient).orElse(null);
		f.setClient(c);
		f.setDateFacture(new Date());
		Facture ff = factureRepository.save(f);

		for(DetailFacture df: ff.getDetailFactures() )
			dfactureRepository.save(df);
		return ff;
	}*/

}
