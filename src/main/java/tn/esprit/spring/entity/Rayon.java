package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Rayon implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idRayon; 
	private String codeRayon;
	private String libelleRayon;
	@JsonManagedReference(value = "rayon")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="rayon")
	private List<Produit> produits;
}
