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

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.service.IserviceClient;

@RestController
public class ClientRestController {

	@Autowired
	IserviceClient clientservice;
	
	@GetMapping("/clients")
	@ResponseBody
	public List<Client> getClients(){
		return clientservice.retrieveAllClients();
	}
	
	
	@GetMapping("/client/{id}")
	@ResponseBody
	public Client getClientById(@PathVariable("id") Long id){
		return clientservice.retrieveClient(id);
	}
	
	@PostMapping("/addClient")
	@ResponseBody
	public Client addClient(@RequestBody Client c) {
		return clientservice.addClient(c);
	}
	
	// http://localhost:8089/SpringMVC/client/remove-client/{client-id}
	@DeleteMapping("/remove-client/{client-id}")
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long clientId) {
	clientservice.deleteClient(clientId);
	}

	// http://localhost:8089/SpringMVC/client/modify-client
	@PutMapping("/modify-client")
	@ResponseBody
	public Client modifyClient(@RequestBody Client client) {
	return clientservice.updateClient(client);
	}
}
