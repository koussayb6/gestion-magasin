package tn.esprit.spring.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class CodePromo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idCodePromo; 
	private String code;
	private int pourcentageRemise;
	private float montantMin;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private CategorieProduit categorieProduit;	
	@Temporal(TemporalType.DATE)
	private Date dateExpiration;
	
	@JsonBackReference(value="codesPromo")
	@ManyToOne
	private Client client;
}
