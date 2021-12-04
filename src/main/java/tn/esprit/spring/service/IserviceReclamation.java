package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Reclamation;

public interface IserviceReclamation {
	
	List<Reclamation> retriveAllReclamations();
	Reclamation addReclamation(Reclamation r);
	void deleteReclamation(Long id);
	Reclamation updateReclamation(Reclamation r);
	Reclamation retriveReclamation(Long id);
	Reclamation updateStatuOftheReclamation(Reclamation r);



}
