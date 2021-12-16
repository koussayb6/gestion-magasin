package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class Panier implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idPanier;
	@Enumerated(EnumType.STRING)
	private PanierStatus panierStatus;
	private int quantite ;
//	@JsonManagedReference(value="panierclient")
//	@ManyToOne
//	private Client client ;
	
	/*@JsonManagedReference(value="panierproduit")
	@ManyToOne
	private Produit produit ;*/

}
