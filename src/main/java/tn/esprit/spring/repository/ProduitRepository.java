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
		List<Client> qqfesc80rqna4ljmv0ga2m5m6f58twm7nqc27h0gx2(@Param("profession") Profession profession);*/
		
		@Query("select sum(df.prixTotal) from Facture f "
				+ "join f.detailFactures df "
				+ "join df.produit p "
				+ "join p.detailProduit dp "
				+ "where dp.categorieProduit = ?1 "
				+ "and f.dateFacture between ?2 and ?3")
		float getRevenuBrutCategorieProduit(CategorieProduit cat, Date startDate, Date endDate);

}