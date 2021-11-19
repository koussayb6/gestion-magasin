package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.repository.RayonRepository;

@Service
public class ServiceRayonImpl implements IserviceRayon {
	@Autowired
	RayonRepository rp;
	
	@Override
	public List<Rayon> retrieveAllRayons() {
		return rp.findAll();
	}

	@Override
	public Rayon addRayon(Rayon r) {
		rp.save(r);
		return r;
	}

	@Override
	public Rayon updateRayon(Rayon r) {
		rp.save(r);
		return r;
	}

	@Override
	public Rayon retrieveRayon(Long id) {
		Rayon r = rp.findById(id).orElse(null);
		return r;
	}

	@Override
	public void deleteRayon(Long id) {
		rp.deleteById(id);
		
	}

}
