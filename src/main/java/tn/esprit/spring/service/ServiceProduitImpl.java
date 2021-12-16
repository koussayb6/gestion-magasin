package tn.esprit.spring.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.ClientRepository;
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
				query.orderBy( criteriaBuilder.desc(root.get("idProduit")));
				return p;
			}}, pageable).getContent();


	}
	@Override
	public Produit getProduit(Long id) {
		return produitRepository.findById(id).orElse(null);
	}
	@Override
	public void deleteProduit(Long id) {
		produitRepository.deleteById(id);
	}


	@Override
	@Transactional
	public Produit addProduit(Produit p, Long idRayon, Long idStock, CategorieProduit cat, MultipartFile file) throws IOException {
		Rayon rayon= rayonRepository.findById(idRayon).orElse(null);
		Stock stock= stockRepository.findById(idStock).orElse(null);
		p.setRayon(rayon);
		p.setStock(stock);
		if(file!=null)
			p.setPicByte(file.getBytes());
		DetailProduit dp= saveDetailProduit(p, cat);
		p.setDetailProduit(dp);
		Produit pr=produitRepository.save(p);
		return pr;
	}
	@Override
	@Transactional
	public Produit updateProduit(Long idProduit, Produit p, Long idRayon, Long idStock, CategorieProduit cat, MultipartFile file) throws IOException {
		Rayon rayon= rayonRepository.findById(idRayon).orElse(null);
		Stock stock= stockRepository.findById(idStock).orElse(null);
		p.setRayon(rayon);
		p.setStock(stock);
		if(file!=null)
			p.setPicByte(compressBytes(file.getBytes()) );
		DetailProduit dp= saveDetailProduit(p, cat);
		p.setDetailProduit(dp);
		produitRepository.save(p);
		return p;
	}
	private DetailProduit saveDetailProduit(Produit p, CategorieProduit cat) {
		if(p.getDetailProduit().getDateCreation()==null) {
			p.getDetailProduit().setDateCreation(new Date());
			p.getDetailProduit().setDateDerniereModification(new Date());
			p.getDetailProduit().setCategorieProduit(cat);
		}else {
			p.getDetailProduit().setDateDerniereModification(new Date());
			p.getDetailProduit().setCategorieProduit(cat);
		}
		DetailProduit dp = detailProduitRepository.save(p.getDetailProduit());
		return dp;

	}

	/*private DetailProduit saveDetailProduit(Produit p, CategorieProduit cat) {
		if(p.getDetailProduit()==null) {
			DetailProduit dp= new DetailProduit();
			dp.setDateCreation(new Date());
			dp.setDateDerniereModification(new Date());
			dp.setCategorieProduit(cat);
			p.setDetailProduit(dp);
		}else {
			p.getDetailProduit().setDateDerniereModification(new Date());
			p.getDetailProduit().setCategorieProduit(cat);

		}

		DetailProduit dp = detailProduitRepository.save(p.getDetailProduit());
		return dp;

	}*/

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
	



	@Override
	public float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate) {
		Produit p= produitRepository.findById(idProduit).orElse(null);
		float rb=0;
		for(DetailFacture df: p.getDetailFactures()) {
			if(df.getFacture().getDateFacture().after(startDate) && df.getFacture().getDateFacture().before(endDate) ) {
				rb+= df.getQte()*p.getPrixUnitaire();
			}
		}

		return rb;
	}

	@Override
	public float getRevenuBrutCategorieProduit(CategorieProduit cat, Date startDate, Date endDate) {
		/*float rb=0;
		for(Produit p: produitRepository.findAll()) {
			if(p.getDetailProduit().getCategorieProduit()==cat) {
				for(DetailFacture df: p.getDetailFactures()) {
					if(df.getFacture().getDateFacture().after(startDate) && df.getFacture().getDateFacture().before(endDate) ) {
						rb+= df.getPrixTotal();
					}
				}
			}
		}*/

		return produitRepository.getRevenuBrutCategorieProduit(cat, startDate, endDate);
	}

	@Override
	public List<Produit> pic(Long id) {
		Produit p= produitRepository.findById(id).orElse(null);
		for(Produit pr: produitRepository.findAll()) {
			pr.setPicByte(p.getPicByte() );
			produitRepository.save(p);
		}
		return produitRepository.findAll();
	}
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
	

}

