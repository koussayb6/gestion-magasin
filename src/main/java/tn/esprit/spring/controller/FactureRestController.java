package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.IserviceFacture;
@RestController
public class FactureRestController {
	
	@Autowired
	IserviceFacture serviceFacture;
	
	@GetMapping("/factures")
	@ResponseBody
	@CrossOrigin
	public List<Facture> getFactures(){
		return serviceFacture.retrieveAllFactures();
	}
	
	@GetMapping("/facture/{id}")
	@ResponseBody
	@CrossOrigin
	public Facture getFacture(@PathVariable("id") Long factureId){
		return serviceFacture.retrieveFacture(factureId);
	}
	
	
	@PutMapping("/cancelfacture")
	@ResponseBody
	@CrossOrigin
	public Facture modifyFacture(@RequestBody Facture facture) {
	return serviceFacture.cancelFacture(facture.getIdFacture());
	}
	
	@PostMapping("/addfacture/{id}")
	@ResponseBody
	@CrossOrigin
	public Facture addfacture(@RequestBody Facture f ,@PathVariable("id") Long clientId, @RequestParam(required=false, value="code") String code){
		return serviceFacture.addfacture( f , clientId, code );
	}
	
	@GetMapping("/produitfactures/{id}")
	@ResponseBody
	@CrossOrigin
	public List<Facture> getProduitFacture(@PathVariable("id") Long idProduit) {
		return serviceFacture.getProduitFacture(idProduit);
	}

	@GetMapping("/categoriefactures")
	@ResponseBody
	@CrossOrigin
	public List<Facture> getCategorieFacture(@RequestParam(value="categorie") CategorieProduit cat) {
		return serviceFacture.getCategorieFacture(cat);
	}
	
	@GetMapping("/getfactures")
	@ResponseBody
	@CrossOrigin
	public List<Facture> getFactures(@RequestParam(required=false, value="prixmin") Float prixmin, @RequestParam(required=false, value="prixmax") Float prixmax,
			@RequestParam(required=false, value="datedebut") Date datedebut, @RequestParam(required=false, value="datefin") Date datefin) {
		return serviceFacture.getFactures(prixmin, prixmax, datedebut, datefin);
	}
}