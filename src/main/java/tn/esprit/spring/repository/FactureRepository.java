package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

	@Query("select f from Facture f "
			+ "join f.detailFactures df "
			+ "join df.produit p "
			+ "where p.idProduit = ?1 ")
	List<Facture> getProduitFacture(Long idProduit);
	
}
