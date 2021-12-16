package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.PanierStatus;
import tn.esprit.spring.service.IservicePannier;

@RestController
public class PanierRestController {

	@Autowired
	IservicePannier servicepanier;

	@GetMapping("/panier/{id}")
	@ResponseBody
	@CrossOrigin
	public List<Panier> getPanier(@PathVariable("id") Long ClientId){
		List<Panier> list = servicepanier.retrieveByClinet(ClientId);
		List<Panier> list2= new ArrayList();
		for (Panier p : list) {
			if (p.getPanierStatus()==PanierStatus.PANIER ) {
				list2.add(p);
			}
		}

		return list2;
	}

	@GetMapping("/addpanier/{idClient}/{idProduit}/{qte}")
	@ResponseBody
	@CrossOrigin
	public Panier addpanier(@PathVariable("idClient") Long clientId,@PathVariable("idProduit") Long produitId,@PathVariable("qte") int qte){
		return servicepanier.addPanier(clientId, produitId, qte);
	}

	@PutMapping("/modifierstatus")
	@ResponseBody
	@CrossOrigin
	public void modifierstatus(Panier p,@RequestParam PanierStatus status) {

		servicepanier.changeStatus(p, status);
	}
}
