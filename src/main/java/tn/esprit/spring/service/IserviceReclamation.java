package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Notifications;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.TypeReclamation;

public interface IserviceReclamation {
	
	List<Reclamation> retriveAllReclamations();
	Reclamation addReclamation(Reclamation r);
	void deleteReclamation(Long id);
	Reclamation updateReclamation(Reclamation r);
	Reclamation retriveReclamation(Long id);
	Reclamation confirmeReclama(Reclamation r);
	Reclamation refuseReclama(Reclamation r);
	List<Notifications> retriveAllNotif();
	List<Reclamation> filterReclama(Long idClient , String status , TypeReclamation tr);
	List<Reclamation> getRecByClient(Long idclient);

	
	



}
