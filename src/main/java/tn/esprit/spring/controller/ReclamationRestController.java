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

import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.service.IserviceReclamation;

@RestController
public class ReclamationRestController {
	@Autowired
	IserviceReclamation reclamationservice;
	
	
	
	@GetMapping("/reclamations")
	@ResponseBody
	public List<Reclamation> getReclamations(){
		return reclamationservice.retriveAllReclamations();
	}
	
	
	@GetMapping("/reclamation/{id}")
	@ResponseBody
	public Reclamation getReclamationById(@PathVariable("id") Long id){
		return reclamationservice.retriveReclamation(id);
	}
	
	@PostMapping("/addReclamation")
	@ResponseBody
	public Reclamation addReclamation(@RequestBody Reclamation r) {
		return reclamationservice.addReclamation(r);
	}
	
	
	@DeleteMapping("/deletereclamation/{reclamation-id}")
	@ResponseBody
	public void removeReclamation(@PathVariable("reclamation-id") Long id) {
	reclamationservice.deleteReclamation(id);;
	}


	@PutMapping("/updatereclamation")
	@ResponseBody
	public Reclamation modifyReclamation(@RequestBody Reclamation reclamation) {
	return reclamationservice.updateReclamation(reclamation);
	}
	
	@PutMapping("/updatestatus")
	@ResponseBody
	public Reclamation updateStatus(@RequestBody Reclamation reclamation) {
	return reclamationservice.updateStatuOftheReclamation(reclamation);
	}
	
	
	
}
