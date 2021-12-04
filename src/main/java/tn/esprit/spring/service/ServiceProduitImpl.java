package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.DetailProduitRepository;
import tn.esprit.spring.repository.FournisseurRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;
import tn.esprit.spring.repository.StockRepository;
@Service
public class ServiceProduitImpl implements IserviceProduit {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	RayonRepository rayonRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	DetailProduitRepository detailProduitRepository;
	@Autowired
	FournisseurRepository fournisseurRepository;
	
	public List<Produit> retrieveAllProduits(Float minPrix, Float maxPrix, String libelle, org.springframework.data.domain.Pageable pageable) {
		return produitRepository.findAll(new Specification<Produit>() {

			@Override
			public Predicate toPredicate(Root<Produit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate p = criteriaBuilder.conjunction();
	            if (minPrix!=null) {
	                p = criteriaBuilder.and(p, criteriaBuilder.greaterThanOrEqualTo(root.get("prixUnitaire"), minPrix.floatValue()));
	            }
	            if (maxPrix!=null) {
	                p = criteriaBuilder.and(p, criteriaBuilder.lessThanOrEqualTo(root.get("prixUnitaire"), maxPrix.floatValue()));
	            }
	            if (!StringUtils.isEmpty(libelle)) {
	                p = criteriaBuilder.and(p, criteriaBuilder.like(root.get("libelle"), "%" + libelle + "%"));
	            }
	           // cq.orderBy(cb.desc(root.get("name")), cb.asc(root.get("id")));
	            return p;
	        }}, pageable).getContent();
				
		
	}

	@Override
	@Transactional
	public Produit addProduit(Produit p, Long idRayon, Long idStock) {
		Rayon rayon= rayonRepository.findById(idRayon).orElse(null);
		Stock stock= stockRepository.findById(idStock).orElse(null);
		p.setRayon(rayon);
		p.setStock(stock);
		DetailProduit dp= saveDetailProduit(p);
		p.setDetailProduit(dp);
		produitRepository.save(p);
		return p;
	}
	/*private DetailProduit saveDetailProduit(Produit p) {
		if(p.getDetailProduit().getDateCreation()==null) {
			p.getDetailProduit().setDateCreation(new Date());
			p.getDetailProduit().setDateDerniereModification(new Date());
		}else {
			p.getDetailProduit().setDateDerniereModification(new Date());
		}
		DetailProduit dp = detailProduitRepository.save(p.getDetailProduit());
		return dp;

	}*/
	
	private DetailProduit saveDetailProduit(Produit p) {
		if(p.getDetailProduit()==null) {
			DetailProduit dp= new DetailProduit();
			dp.setDateCreation(new Date());
			dp.setDateDerniereModification(new Date());
			p.setDetailProduit(dp);
		}else {
			p.getDetailProduit().setDateDerniereModification(new Date());
		}
		
		DetailProduit dp = detailProduitRepository.save(p.getDetailProduit());
		return dp;

	}

	@Override
	public Produit retrieveProduit(Long id) {
		return produitRepository.findById(id).orElse(null);
	}

	@Override
	public void affectProduitToStock(Long idProduit, Long idStock) {
		Produit p= produitRepository.findById(idProduit).orElse(null);
		Stock s= stockRepository.findById(idStock).orElse(null);
		p.setStock(s);
		produitRepository.save(p);
	}
	@Override
	public void affectProduitToFournisseur(Long idProduit, Long idFourn) {
		Produit p= produitRepository.findById(idProduit).orElse(null);
		Fournisseur f= fournisseurRepository.findById(idFourn).orElse(null);
		p.getFournisseurs().add(f);
		produitRepository.save(p);
	}

}
