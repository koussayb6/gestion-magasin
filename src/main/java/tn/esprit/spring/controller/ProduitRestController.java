package tn.esprit.spring.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.IserviceProduit;
@RestController
public class ProduitRestController {

	@Autowired
	IserviceProduit serviceproduit;
    private static final String DATE_PATTERN = "yyyy/MM/dd";


	@GetMapping("produits")
	@ResponseBody
	@CrossOrigin
	public List<Produit> getAllProduit(@RequestParam(required = false) Float minPrix, @RequestParam(required = false) Float maxPrix,@RequestParam(required = false) String libelle, org.springframework.data.domain.Pageable pageable){

		return serviceproduit.retrieveAllProduits( minPrix,  maxPrix,  libelle,  pageable);

	}
	
	@GetMapping("revenueP/{id-prod}")
	@CrossOrigin
	public float getRevenuBrutProduit(@PathVariable("id-prod") Long idProduit, @RequestParam
    @DateTimeFormat(pattern = DATE_PATTERN) Date fromDate, @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) Date toDate) {
		return serviceproduit.getRevenuBrutProduit(idProduit, fromDate, toDate);
	}
	
	@GetMapping("revenueC/{cat}")
	@CrossOrigin
	public float getRevenuBrutProduit(@PathVariable("cat") CategorieProduit cat, @RequestParam
    @DateTimeFormat(pattern = DATE_PATTERN) Date fromDate, @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) Date toDate) {
		return serviceproduit.getRevenuBrutCategorieProduit(cat, fromDate, toDate);
	}



	@PostMapping("addproduit/{id-rayon}/{id-stock}")
	@ResponseBody
	@CrossOrigin
	public Produit addProduit(@RequestBody Produit p ,@PathVariable("id-rayon") Long idRayon,@PathVariable("id-stock") Long idStock,@RequestParam(required = false) CategorieProduit cat){
		if( !Arrays.asList(CategorieProduit.values()).contains(cat)) {
			return null;
		}
		return serviceproduit.addProduit(p, idRayon, idStock, cat);
	}


	@PutMapping("/produit/affectToStock/{id-prod}/{id-stock}")
	@ResponseBody
	@CrossOrigin
	public void affectToStock(@PathVariable("id-prod") Long prodId, @PathVariable("id-stock") Long stockId) {
		serviceproduit.affectProduitToStock(prodId, stockId);
	}
	
	@PutMapping("/produit/affectToFournisseur/{id-prod}/{id-fourn}")
	@ResponseBody
	@CrossOrigin
	public void affectToFourn(@PathVariable("id-prod") Long prodId, @PathVariable("id-fourn") Long fournId) {
		serviceproduit.affectProduitToFournisseur(prodId, fournId);
	}

}
