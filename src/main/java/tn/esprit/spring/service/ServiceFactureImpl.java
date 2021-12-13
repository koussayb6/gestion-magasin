package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	ClientRepository clientRepository;
	@Autowired
	DetailFactureRepository detailFactureRepository;
	@Autowired
	ProduitRepository produitRepository;
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
		Facture ff = factureRepository.save(f);

		float montant=0;
		float montantremise=0;
		for(DetailFacture df: ff.getDetailFactures() )
		{
			Produit p= produitRepository.findById(df.getProduit().getIdProduit()).orElse(null);
			float pt= 10f*(float)df.getQte();
			df.setPrixTotal(pt);
			df.setMontantRemise(pt*(Float.valueOf(String.valueOf(df.getPourcentageRemise())))/100f);
			DetailFacture df1= detailFactureRepository.save(df);

			montant+=pt-df1.getMontantRemise();
			montantremise+=df1.getMontantRemise();
			//detailFactureRepository.save(df);
		}
		ff.setMontantFacture(montant);
		ff.setMontantRemise(montantremise);
		factureRepository.save(ff);

		return ff;
	}
	/*@Override
	public List<Produit> addfacture(Facture f, long idClient) {
		Client c = clientRepository.findById(idClient).orElse(null);
		f.setClient(c);
		f.setDateFacture(new Date());
		Facture ff = factureRepository.save(f);

		List<Produit> lp=new ArrayList<>();
		for(DetailFacture df: ff.getDetailFactures() )
		{	
			lp.add(produitRepository.findById(df.getProduit().getIdProduit()).orElse(null) );
		}
		

		return lp;
	}*/

}
