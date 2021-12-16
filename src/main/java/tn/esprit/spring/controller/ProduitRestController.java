package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		List<Produit> list= serviceproduit.retrieveAllProduits( minPrix,  maxPrix,  libelle,  pageable);
		/*for(Produit pr:list) {
			if(pr.getPicByte()!=null)
				pr.setPicByte(serviceproduit.decompressBytes(pr.getPicByte()));
		}*/
		return list;

	}
	@GetMapping("produit/{id-prod}")
	@CrossOrigin
	public Produit getProd(@PathVariable("id-prod") Long idProduit) {
		return serviceproduit.getProduit(idProduit);
	}
	
	@GetMapping("revenueP/{id-prod}")
	@CrossOrigin
	public float getRevenuBrutProduit(@PathVariable("id-prod") Long idProduit, @RequestParam
    @DateTimeFormat(pattern = DATE_PATTERN) Date fromDate, @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) Date toDate) {
		return serviceproduit.getRevenuBrutProduit(idProduit, fromDate, toDate);
	}
	@GetMapping("pic/{id-prod}")
	@CrossOrigin
	public List<Produit> pic(@PathVariable("id-prod") Long idProduit) {
		return serviceproduit.pic(idProduit);
	}
	
	@GetMapping("revenueC")
	@ResponseBody
	@CrossOrigin
	public float getRevenuBrutProduit(@RequestParam("cat") CategorieProduit cat, 
			@RequestParam("from") @DateTimeFormat(pattern = DATE_PATTERN) Date fromDate, 
			@RequestParam("to") @DateTimeFormat(pattern = DATE_PATTERN) Date toDate) {
		return serviceproduit.getRevenuBrutCategorieProduit(cat, fromDate, toDate);
	}



	@PostMapping("addproduit/{id-rayon}/{id-stock}")
	@ResponseBody
	@CrossOrigin
	public Produit addProduit(
			@RequestParam("p") String p ,
			@PathVariable("id-rayon") Long idRayon,
			@PathVariable("id-stock") Long idStock,
			@RequestParam(required = false) CategorieProduit cat, 
			@RequestParam(required=false) MultipartFile file) throws IOException{
		if(cat!=null && !Arrays.asList(CategorieProduit.values()).contains(cat)) {
			return null;
		}
		Produit pr= new ObjectMapper().readValue(p, Produit.class);
		return serviceproduit.addProduit(pr, idRayon, idStock, cat, file);
	}
	@PutMapping("updateproduit/{id-rayon}/{id-stock}")
	@ResponseBody
	@CrossOrigin
	public Produit editProduit(
			@RequestParam("p") String p , @RequestParam("id") Long id,
			@PathVariable("id-rayon") Long idRayon,
			@PathVariable("id-stock") Long idStock,
			@RequestParam(required = false) CategorieProduit cat, 
			@RequestParam(required=false) MultipartFile file) throws IOException{
		if(cat!=null && !Arrays.asList(CategorieProduit.values()).contains(cat)) {
			return null;
		}
		Produit pr= new ObjectMapper().readValue(p, Produit.class);
		pr.setIdProduit(id);
		return serviceproduit.addProduit(pr, idRayon, idStock, cat, file);
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
	
	@DeleteMapping("/deleteproduit/{id-prod}")
	@CrossOrigin
	public void deleteProd(@PathVariable("id-prod") Long id) {
		serviceproduit.deleteProduit(id);
	}

}
