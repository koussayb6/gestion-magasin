package tn.esprit.spring.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.service.IserviceClient;

@RestController
public class ClientRestController {

	@Autowired
	IserviceClient clientservice;
	@Autowired
	ClientRepository repo;
	
	@GetMapping("/clients")
	@ResponseBody
	@CrossOrigin
	public List<Client> getClients(){
		return clientservice.retrieveAllClients();
	}
	@GetMapping("/topcategorie/{id-client}")
	@ResponseBody
	@CrossOrigin
	public CategorieProduit topcategorie(@PathVariable("id-client") Long id){
		return repo.getMostBoughtCategorie(id);
	}
	
	
	@GetMapping("/client/{id}")
	@ResponseBody
	@CrossOrigin
	public Client getClientById(@PathVariable("id") Long id){
		return clientservice.retrieveClient(id);
	}
	
	@PostMapping("/addClient")
	@ResponseBody
	@CrossOrigin
	public Client addClient(@RequestBody Client c) {
		return clientservice.addClient(c);
	}
	
	// http://localhost:8089/SpringMVC/client/remove-client/{client-id}
	@DeleteMapping("/remove-client/{client-id}")
	@ResponseBody
	@CrossOrigin
	public void removeClient(@PathVariable("client-id") Long clientId) {
	clientservice.deleteClient(clientId);
	}

	// http://localhost:8089/SpringMVC/client/modify-client
	@PutMapping("/modify-client")
	@ResponseBody
	@CrossOrigin
	public Client modifyClient(@RequestBody Client client) {
	return clientservice.updateClient(client);
	}
	@CrossOrigin
	@GetMapping("/client/addfavorie/{id-client}/{id-produit}")
	@ResponseBody
	public List<Produit> addFavorie(@PathVariable("id-client") Long clientId, @PathVariable("id-produit") Long produitId) {
		return clientservice.addFavorie(clientId, produitId);
	}
	
	@GetMapping("/client/promo/{id-client}")
	@ResponseBody
	public String sendPromo(@PathVariable("id-client") Long clientId)  throws MessagingException{
		return clientservice.sendCodePromo(clientId);
		
	}
	
}
