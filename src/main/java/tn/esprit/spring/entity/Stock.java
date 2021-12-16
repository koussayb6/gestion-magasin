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
import lombok.Builder;
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
@Builder
public class Stock implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idStock; 
	private int qteStock;
	private int qteMin;
	private String libelleStock;
	@JsonManagedReference(value="stock")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="stock")
	private List<Produit> produits;
}
