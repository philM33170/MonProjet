package com.mycompany.tennis.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Epreuve {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private short annee;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TOURNOI")
	private Tournoi tournoi;
	@Column(name = "TYPE_EPREUVE")
	private Character typeEpreuve;
	@ManyToMany
	@JoinTable(name = "participants", 
			   joinColumns = {@JoinColumn(name = "ID_EPREUVE")},
			   inverseJoinColumns = {@JoinColumn(name = "ID_JOUEUR")}
	)
	private Set<Joueur> participants;
	
	public Set<Joueur> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<Joueur> participants) {
		this.participants = participants;
	}
		
	public Character getTypeEpreuve() {
		return typeEpreuve;
	}
	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public short getAnnee() {
		return annee;
	}
	public void setAnnee(short annee) {
		this.annee = annee;
	}
	public Tournoi getTournoi() {
		return tournoi;
	}
	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
		
}
