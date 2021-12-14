package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;
	@JsonManagedReference(value = "produit")
	@OneToMany(mappedBy="produit")
	private List<DetailFacture> detailFactures;
	
	@JsonManagedReference(value = "detailProduit")
	@OneToOne(cascade=CascadeType.ALL)
	private DetailProduit detailProduit;

	@ManyToMany
	private Set<Client> favories;
	@ManyToMany
	private Set<Fournisseur> fournisseurs;
	
	@JsonBackReference(value="stock")
	@ManyToOne
	private Stock stock;
	
	@JsonBackReference(value = "rayon")
	@ManyToOne
	private Rayon rayon;



}
