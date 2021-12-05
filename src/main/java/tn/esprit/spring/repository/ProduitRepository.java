package tn.esprit.spring.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Produit;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>, JpaSpecificationExecutor<Produit> {

		/*@Query("SELECT c FROM Client c WHERE c.profession= :profession")
		List<Client> retrieveClientsByProfession(@Param("profession") Profession profession);*/
		
		@Query(value="select sum(df.prix_total) from facture f "
				+ "join detail_facture df on df.facture_id_facture = f.id_facture "
				+ "join produit p on df.produit_id_produit = p.id_produit "
				+ "join detail_produit dp on dp.id_detail_produit = p.detail_produit_id_detail_produit "
				+ "where dp.categorie_produit = \"?1\" "
				+ "and f.date_facture between '?2' and '?3' ", nativeQuery=true)
		float getRevenuBrutCategorieProduit(CategorieProduit cat, Date startDate, Date endDate);

}
