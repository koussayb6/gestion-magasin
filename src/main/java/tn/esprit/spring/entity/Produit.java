package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Produit implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idProduit; 
	private String code;
	private String libelle;
	private float prixUnitaire;
	@OneToMany(mappedBy="produit")
	private Set<DetailFacture> detailFactures;
	@OneToOne
	private DetailProduit detailProduit;

	@ManyToMany
	private Set<Fournisseur> fournisseurs;

	@ManyToOne
	private Stock stock;

	@ManyToOne
	private Rayon rayon;




}
