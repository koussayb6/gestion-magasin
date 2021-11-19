package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

}
