package tn.esprit.spring.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.ProduitRepository;
@Service
public class ServiceClientImpl implements IserviceClient{
	
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ProduitRepository produitRepository;
	 @Autowired
	private EmailSenderService emailService;
	 
	@Override
	public List<Client> retrieveAllClients() {
		List<Client> clients= (List<Client>) clientRepository.findAll();
		return clients ;
	}

	@Override
	public Client addClient(Client c) {
		
		return clientRepository.save(c);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public Client updateClient(Client c) {
		
		return clientRepository.save(c);
	}

	@Override
	public Client retrieveClient(Long id) {
		
		return clientRepository.findById(id).orElse(null) ;
	}

	@Override
	public List<Produit> addFavorie(Long idClient, Long idProduit) {
		Produit p= produitRepository.findById(idProduit).orElse(null);
		Client c= clientRepository.findById(idClient).orElse(null);
		c.getFavories().add(p);
		clientRepository.save(c);
		return c.getFavories();
		
	}
	
	
	@Override
	public String sendCodePromo(Long idClient) throws MessagingException {
		Client c = clientRepository.findById(idClient).orElse(null);
		int total=0;
		for (Facture F : c.getFactures()){
			total+=F.getMontantFacture();
		}
		if (total < 1000) {
			return "nothing will be recived";
		}
		else{
			emailService.sendEmailWithAttachment("ahmedoussemabenhmida@icloud.com",
					" CODE PROMO 4F5DDZ22",
					"Congratulation !!!");
			
			return "email will be send";
		}
		
		
	}

	
}
