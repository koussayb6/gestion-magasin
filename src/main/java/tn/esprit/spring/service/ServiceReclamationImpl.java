package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Notifications;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.TypeReclamation;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.NotificationsRepository;
import tn.esprit.spring.repository.ReclamationRepository;
@Service
public class ServiceReclamationImpl implements IserviceReclamation{
	
	@Autowired
	ReclamationRepository rp;
	@Autowired
	NotificationsRepository notifrp;
	@Autowired
	ClientRepository cr;
	@Override
	public List<Reclamation> retriveAllReclamations() {
		
		List<Reclamation> list = (List<Reclamation>) rp.findAll();
		return list;
	}

	@Override
	public Reclamation addReclamation(Reclamation r) {
		
		r.setStatue("En cours de traimtement");
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

	@Override
	public List<Reclamation> filterReclama(Long idClient, String status, TypeReclamation tr) {
		List<Reclamation> list = new ArrayList<>();

		if (idClient!=null&status==null&tr==null) {
			Client c = cr.findById(idClient).orElse(null);
			list=c.getReclamations();
			}
		
		if(status!=null&tr==null&idClient==null){
			for(Reclamation r : rp.findAll()){
				if(r.getStatue().equals(status)){
					list.add(r);
			}
				}
		}
		if(tr!=null&status==null&idClient==null){
			for(Reclamation r : rp.findAll()){
				if(r.getTypereclamation()==tr){
					list.add(r);
			}
				}
		}
		if(status!=null&tr!=null&idClient==null){
			for(Reclamation r : rp.findAll()){
				if(r.getStatue().equals(status)&r.getTypereclamation()==tr){
					list.add(r);
			}
			}
		}
		if(tr==null&status!=null&idClient!=null){
			Client c = cr.findById(idClient).orElse(null);
			for(Reclamation r : c.getReclamations()){
				if(r.getStatue().equals(status)&r.getClient().getIdClient()==idClient){
					list.add(r);
			}
				}
		}
		if(tr!=null&status==null&idClient!=null){
			Client c = cr.findById(idClient).orElse(null);
			for(Reclamation r : c.getReclamations()){
				if(r.getTypereclamation()==tr&r.getClient().getIdClient()==idClient){
					list.add(r);
			}
				}
		}
		if(tr!=null&status!=null&idClient!=null){
			Client c = cr.findById(idClient).orElse(null);
			for(Reclamation r : c.getReclamations()){
				if(r.getTypereclamation()==tr&r.getClient().getIdClient()==idClient&r.getStatue().equals(status)){
					list.add(r);
			}
				}
		}
				return list;
	}

	@Override
	public List<Reclamation> getRecByClient(Long idclient) {
		Client c = cr.findById(idclient).orElse(null);
		
		
		return c.getReclamations();
	}
	
	
	

}
