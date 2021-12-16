package tn.esprit.spring.service;

import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;

public interface IserviceClient {
	
	List<Client> retrieveAllClients();

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(Client c);

	Client retrieveClient(Long id);
	
	List<Produit> addFavorie(Long idClient, Long idProduit);
	
	

}
