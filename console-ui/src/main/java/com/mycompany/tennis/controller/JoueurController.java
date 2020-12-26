package com.mycompany.tennis.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

public class JoueurController {

	private JoueurService joueurService;
	
	public JoueurController() {
		this.joueurService = new JoueurService();
	}
	
	public void afficheDetailsJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les informations ? ");
		long identifiant = scanner.nextLong();
		Joueur joueur = joueurService.getJoueur(identifiant);
		System.out.println("Le joueur sélectionné s'appelle " + joueur.getPrenom() + " " + joueur.getNom());
	}
	
	public void creerJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est le prénom du nouveau joueur que vous souhaitez ajouter ? ");
		String prenom = scanner.nextLine();
		System.out.println("Quel est le nom du nouveau joueur que vous souhaitez ajouter ? ");
		String nom = scanner.nextLine();
		System.out.println("Quel est le sexe du nouveau joueur que vous souhaitez ajouter ? ");		
		char sexe = scanner.nextLine().charAt(0);		
		Joueur joueur = new Joueur();
		joueur.setPrenom(prenom);
		joueur.setNom(nom);
		joueur.setSexe(sexe);
		joueurService.createJoueur(joueur);
		System.out.println("Le joueur " + joueur.getPrenom() + " " + joueur.getNom() + "(" + joueur.getSexe()+") a été créé et son identifiant est " + joueur.getId());
	}
	
	public void renommeJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du joueur que vous voulez renommer ? ");
		long identifiant = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Quel est le nouveau nom ? ");
		String nom = scanner.nextLine();
		joueurService.renomme(identifiant, nom);
	}
	
	public void changerSexeJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du joueur dont vous voulez changer le sexe ? ");
		long identifiant = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Quel est le nouveau sexe de ce joueur ? ");
		char sexe = scanner.nextLine().charAt(0);
		joueurService.changementSexe(identifiant, sexe);
	}
	
	public void supprimeJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du joueur à supprimer ? ");
		long identifiant = scanner.nextLong();
		
		joueurService.deleteJoueur(identifiant);
	}
	
	public void afficheListeJoueurs() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Voulez vous une liste hommes(H) ou femmes(F) ? ");
		char sexe = scanner.nextLine().charAt(0);
		for (JoueurDto dto : joueurService.getListeJoueurs(sexe)) {
			System.out.println(dto.getPrenom() + " " + dto.getNom());
		}
	}
}
