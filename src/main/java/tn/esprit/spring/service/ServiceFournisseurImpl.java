package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.repository.FournisseurRepository;

@Service

public class ServiceFournisseurImpl implements IserviceFournisseur {
	@Autowired
	FournisseurRepository fp;
	
	
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

}
