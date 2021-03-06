package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DetailFacture implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idDetailFacture; 
	private int qte;
	private float prixTotal;
	private int pourcentageRemise;
	private float montantRemise;
	
	@JsonBackReference(value = "detailFactures")
	@ManyToOne
	private Facture facture;
	
	@JsonBackReference(value = "produit")
	@ManyToOne
	private Produit produit;

	
}
