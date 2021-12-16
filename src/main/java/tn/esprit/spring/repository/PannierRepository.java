package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Panier;
@Repository

public interface PannierRepository extends JpaRepository<Panier, Long>{

}
