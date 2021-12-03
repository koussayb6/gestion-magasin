package tn.esprit.spring.entity;



import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Facture implements Serializable {

	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idFacture; 
	private float montantRemise;
	private float montantFacture;
	@Temporal(TemporalType.DATE)
	private Date dateFacture;
	private boolean active;
	
	@JsonBackReference(value = "factures")
	@ManyToOne
	private Client client;
	
	@JsonManagedReference(value = "detailFactures")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="facture")
	private List<DetailFacture> detailFactures;
	
}
