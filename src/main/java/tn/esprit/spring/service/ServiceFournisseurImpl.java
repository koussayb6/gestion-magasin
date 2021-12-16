package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.FournisseurRepository;
import tn.esprit.spring.repository.ProduitRepository;

@Service

public class ServiceFournisseurImpl implements IserviceFournisseur {
	@Autowired
	FournisseurRepository fp;
	@Autowired
	ProduitRepository prodRepo;
	
	
	@Override
	public List<Fournisseur> retrieveAllFournissuers() {
		return fp.findAll();
	}

	@Override
	public Fournisseur addFournisseur(Fournisseur f) {
		
		return fp.save(f);
	}

	@Override
	public void deleteFournissur(Long id) {
		fp.deleteById(id);
		
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur f) {
		
		return fp.save(f);
	}

	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		Fournisseur f = fp.findById(id).orElse(null);
		
			return f;
	}
	@Override
	public List<Fournisseur> getFournisseurByProduit(CategorieProduit cat){
		List<Fournisseur> list= new ArrayList<>();
		for(Produit p: prodRepo.findAll()){
			if(p.getDetailProduit().getCategorieProduit()==cat){
				list.addAll(p.getFournisseurs());
			}
		}
		Set<Fournisseur> hashset= new HashSet<>(list);
		List<Fournisseur> finalList= new ArrayList<>(hashset);
		return finalList;
	}
}
