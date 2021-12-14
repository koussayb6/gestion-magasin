package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query(value= "SELECT categorie_produit FROM detail_produit dp \r\n"
			+ "JOIN produit p ON p.detail_produit_id_detail_produit= dp.id_detail_produit\r\n"
			+ "JOIN detail_facture df ON df.produit_id_produit = p.id_produit\r\n"
			+ "JOIN facture f  on f.id_facture= df.facture_id_facture \r\n"
			+ "WHERE f.client_id_client= ?1 \r\n"
			+ "GROUP BY dp.categorie_produit ORDER BY SUM(df.prix_total) DESC LIMIT 1",nativeQuery=true)
	CategorieProduit getMostBoughtCategorie(Long clientId);
}
