package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.IserviceProduit;
@RestController
public class ProduitRestController {
	
	@Autowired
	IserviceProduit serviceproduit;

	
	@GetMapping("produits")
	@ResponseBody
	public List<Produit> getAllProduit(){
		
		return serviceproduit.retrieveAllProduits();
	}
	
	
	@PostMapping("addproduit")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit p , Long idRayon, Long idStock){
		return serviceproduit.addProduit(p, idRayon, idStock);
	}
	

}
