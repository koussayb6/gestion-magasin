package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.service.IserviceProduit;
@RestController
public class ProduitRestController {

	@Autowired
	IserviceProduit serviceproduit;


	@GetMapping("produits")
	@ResponseBody
	public List<Produit> getAllProduit(@RequestParam(required = false) Float minPrix, @RequestParam(required = false) Float maxPrix,@RequestParam(required = false) String libelle, org.springframework.data.domain.Pageable pageable){

		return serviceproduit.retrieveAllProduits( minPrix,  maxPrix,  libelle,  pageable);

	}


	@PostMapping("addproduit/{id-rayon}/{id-stock}")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit p ,@PathVariable("id-rayon") Long idRayon,@PathVariable("id-stock") Long idStock){
		return serviceproduit.addProduit(p, idRayon, idStock);
	}


	@PutMapping("/produit/affectToStock/{id-prod}/{id-stock}")
	@ResponseBody
	public void affectToStock(@PathVariable("id-prod") Long prodId, @PathVariable("id-stock") Long stockId) {
		serviceproduit.affectProduitToStock(prodId, stockId);
	}
	
	@PutMapping("/produit/affectToFournisseur/{id-prod}/{id-fourn}")
	@ResponseBody
	public void affectToFourn(@PathVariable("id-prod") Long prodId, @PathVariable("id-fourn") Long fournId) {
		serviceproduit.affectProduitToFournisseur(prodId, fournId);
	}

}
