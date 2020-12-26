package com.mycompany.tennis.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiService;

public class TournoiController {

	private TournoiService tournoiService;
	
	public TournoiController() {
		this.tournoiService = new TournoiService();
	}
	
	public void afficherDetailsTournoi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du tournoi dont vous voulez afficher les informations ? ");
		long identifiant = scanner.nextLong();
		TournoiDto tournoi = tournoiService.getTournoi(identifiant);
		System.out.println("Le tournoi sélectionné s'appelle " + tournoi.getNom() + " et son code est " + tournoi.getCode());
	}
	
	public void creerTournoi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est le nom du tournoi que vous souhaitez ajouter ? ");
		String nom = scanner.nextLine();
		System.out.println("Quel est le code du tounoi que vous souhaitez ajouter ? ");
		String code = scanner.nextLine();
		
		TournoiDto tournoi = new TournoiDto();
		tournoi.setCode(code);
		tournoi.setNom(nom);
		tournoiService.createTournoi(tournoi);
		System.out.println("Le tournoi " + tournoi.getNom() + " dont le code est " + tournoi.getCode() + " est créé");
	}
	
	public void deleteTournoi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du tournoi à supprimer ? ");
		Long identifiant = scanner.nextLong();		
		
		tournoiService.deleteTournoi(identifiant);		
	}
}
