package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.service.IserviceFournisseur;
@RestController
public class FournisseurRestController {
	
	@Autowired
	IserviceFournisseur serviceFournissuer;
	
	
	@GetMapping("/fournisseurs")
	@CrossOrigin
	public List<Fournisseur> getFournissur(){		
		return serviceFournissuer.retrieveAllFournissuers();
		
	}
	
	@PostMapping("/addfournisseur")
	@ResponseBody
	@CrossOrigin
	public Fournisseur addFournisseur(@RequestBody Fournisseur f){
		return serviceFournissuer.addFournisseur(f);
	}
	
	@DeleteMapping("/delfournisseur/{fournisseurId}")
	@ResponseBody
	@CrossOrigin
	public void deleteFournisseur(@PathVariable ("fournisseurId") long fournisseurId){
		serviceFournissuer.deleteFournissur(fournisseurId);
	}
	
	@PutMapping("/updatefournisseur")
	@ResponseBody
	@CrossOrigin
	public Fournisseur updateFournisseur(@RequestBody Fournisseur f){
		return serviceFournissuer.updateFournisseur(f);
		
	}

}
