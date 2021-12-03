package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.service.IserviceFacture;
@RestController
public class FactureRestController {
	
	@Autowired
	IserviceFacture serviceFacture;
	
	@GetMapping("/factures")
	@ResponseBody
	public List<Facture> getFactures(){
		return serviceFacture.retrieveAllFactures();
	}
	
	@GetMapping("/facture/{id}")
	@ResponseBody
	public Facture getFactures(@PathVariable("id") Long factureId){
		return serviceFacture.retrieveFacture(factureId);
	}
	
	
	@PutMapping("/factures")
	@ResponseBody
	public Facture modifyClient(@RequestBody Facture facture) {
	return serviceFacture.cancelFacture(facture.getIdFacture());
	}
}
