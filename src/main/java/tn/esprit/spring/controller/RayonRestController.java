package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.service.IserviceRayon;

@RestController
public class RayonRestController {
	
	@Autowired
	IserviceRayon servicerayon;
	
	
	@GetMapping("/rayons")
	public List<Rayon> getRayons(){
		 return servicerayon.retrieveAllRayons();
			
	}
	
	
	@PostMapping("/addrayon")
	@ResponseBody
	public Rayon addRayon(@RequestBody Rayon r){		
		return servicerayon.addRayon(r);
	}
	
	
	
	@PutMapping("/updaterayon")
	@ResponseBody
	public Rayon updateRayon(@RequestBody Rayon r){
		return servicerayon.updateRayon(r);
	}
	
	
	@DeleteMapping("/deleterayon/{rayonId}")
	@ResponseBody
	public void deleteRayon(@PathVariable ("rayonId") Long rayonId ){
		servicerayon.deleteRayon(rayonId);
		
	}
}
