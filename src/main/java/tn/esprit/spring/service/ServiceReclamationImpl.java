package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Notifications;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.repository.NotificationsRepository;
import tn.esprit.spring.repository.ReclamationRepository;
@Service
public class ServiceReclamationImpl implements IserviceReclamation{
	
	@Autowired
	ReclamationRepository rp;
	@Autowired
	NotificationsRepository notifrp;
	@Override
	public List<Reclamation> retriveAllReclamations() {
		
		List<Reclamation> list = (List<Reclamation>) rp.findAll();
		return list;
	}

	@Override
	public Reclamation addReclamation(Reclamation r) {
		
		return rp.save(r);
	}

	@Override
	public void deleteReclamation(Long id) {
		rp.deleteById(id);
		
	}

	@Override
	public Reclamation updateReclamation(Reclamation r) {
		return rp.save(r);
	}

	@Override
	public Reclamation retriveReclamation(Long id) {
		 return rp.findById(id).orElse(null);
		
	}

	@Override
	public Reclamation confirmeReclama(Reclamation r) {
		
		r.setStatue("Confirmer");
		rp.save(r);
		
		Notifications notif = new Notifications();
		Date d = new Date();
		notif.setNotifDate(d);
		notif.setSubject("Reclamation Confirmed");
		notifrp.save(notif);
	
		return r;
	}

	@Override
	public Reclamation refuseReclama(Reclamation r) {
		r.setStatue("Refuser");
		rp.save(r);
		
		Notifications notif = new Notifications();
		Date d = new Date();
		notif.setNotifDate(d);
		notif.setSubject("Reclamation Refused");
		notifrp.save(notif);
	
		return r;
	}

	@Override
	public List retriveAllNotif() {
		List<Notifications> list = (List<Notifications>) notifrp.findAll();
		return list;
	}
	

}
