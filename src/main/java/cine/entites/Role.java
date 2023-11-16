package cine.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne
	@JoinColumn(name = "ID_ACTEUR", referencedColumnName = "ID_IMDB")
	Acteur acteur;
	
	@ManyToOne
	@JoinColumn(name = "ID_FILM", referencedColumnName = "ID_IMDB")
	Film film;
	
	@Column(name = "PERSONNAGE")
	String personnage;
}
