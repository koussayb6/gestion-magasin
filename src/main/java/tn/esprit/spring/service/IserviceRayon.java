package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Rayon;



public interface IserviceRayon {
	List<Rayon> retrieveAllRayons();

	Rayon addRayon(Rayon r);

	Rayon updateRayon(Rayon r);

	Rayon retrieveRayon(Long id);

	void deleteRayon(Long id);

}
