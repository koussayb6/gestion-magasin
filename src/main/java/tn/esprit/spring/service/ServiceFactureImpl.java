package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.CodePromo;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.CodePromoRepository;
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
	@Autowired
	CodePromoRepository codePromoRepository;
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
	public List<Facture> getFacturesClient(Long idClient){
		Client c =clientRepository.findById(idClient).orElse(null);
		return c.getFactures();
	}



	@Override
	public Facture addfacture(Facture f, Long idClient, String codePromo) {
		Client c = clientRepository.findById(idClient).orElse(null);
		f.setClient(c);
		f.setDateFacture(new Date());
		Facture ff = factureRepository.save(f);
		CodePromo codeP= codePromoRepository.findByCode(codePromo);
		boolean codeValid= codeP!=null 
				&& codeP.getDateExpiration().after(new Date()) 
				&& codeP.isActive() 
				&& codeP.getClient()==c;
		float montantCategorieCode=0;
		float montant=0;
		float montantremise=0;
		for(DetailFacture df: ff.getDetailFactures() )
		{
			Produit p= produitRepository.findById(df.getProduit().getIdProduit()).orElse(null);
			float pt= p.getPrixUnitaire()*(float)df.getQte();
			df.setPrixTotal(pt);
			if( codeValid && p.getDetailProduit().getCategorieProduit()==codeP.getCategorieProduit()) {

				montantCategorieCode+=pt;
			}
			df.setMontantRemise(pt*(Float.valueOf(String.valueOf(df.getPourcentageRemise())))/100f);
			DetailFacture df1= detailFactureRepository.save(df);

			montant+=pt-df1.getMontantRemise();
			montantremise+=df1.getMontantRemise();
		}
		if(codeP!=null&&montantCategorieCode >= codeP.getMontantMin()) {
			montant-=montantCategorieCode*(Float.valueOf(String.valueOf(codeP.getPourcentageRemise())))/100f;
			montantremise+=montantCategorieCode*(Float.valueOf(String.valueOf(codeP.getPourcentageRemise())))/100f;
			codeP.setActive(false);
			codePromoRepository.save(codeP);
		}
		c.setCompteurPromo(c.getCompteurPromo()+montant);
		ff.setMontantFacture(montant);
		ff.setMontantRemise(montantremise);
		factureRepository.save(ff);
		clientRepository.save(c);

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

	@Override
	public List<Facture> getProduitFacture(Long idProduit) {
		List<Facture> list = factureRepository.getProduitFacture(idProduit);

		/*List<Facture> list = new ArrayList<>();
		Produit p = produitRepository.findById(idProduit).orElse(null);
		for (DetailFacture dt : p.getDetailFactures()) {
			list.add(dt.getFacture());
		}*/
		return list;
	}

	@Override
	public List<Facture> getCategorieFacture(CategorieProduit cat) {
		List<Facture> list = new ArrayList<>();
		for (Facture f  : factureRepository.findAll()) {
			for (DetailFacture dt : f.getDetailFactures()) {
				if (dt.getProduit().getDetailProduit().getCategorieProduit()==cat) {
					list.add(f);
					break;
				}
			}
		}
		return list;
	}

	@Override
	public List<Facture> getFactures(Float prixmin, Float prixmax, Date datedebut, Date datefin) {
		Stream<Facture> streamf = factureRepository.findAll().stream();
		if(prixmin !=  null) {
			streamf = streamf.filter(e->e.getMontantFacture()>=prixmin);
			}
			
		if(prixmax !=  null) {
			streamf = streamf.filter(e->e.getMontantFacture()<=prixmax);
		}
		if(datedebut !=  null) {
			streamf = streamf.filter(e->e.getDateFacture().after(datedebut));
		}	
		if(datefin !=  null) {
			streamf = streamf.filter(e->e.getDateFacture().before(datefin));
		}
		
		return streamf.collect(Collectors.toList());
	}
	
	


}

