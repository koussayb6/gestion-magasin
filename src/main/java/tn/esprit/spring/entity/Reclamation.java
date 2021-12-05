package tn.esprit.spring.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

public class Reclamation implements Serializable {
	

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long idReclamation;
	private String subject;
	private String description;
	private String statue;
	@Enumerated(EnumType.STRING)
	private TypeReclamation typereclamation;
	
	
	@JsonBackReference(value = "reclamations")
	@ManyToOne
	private Client client;
	

}
