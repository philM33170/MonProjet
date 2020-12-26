package com.mycompany.tennis.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.service.EpreuveService;

public class EpreuveController {

	private EpreuveService epreuveService;

	public EpreuveController() {		
		this.epreuveService = new EpreuveService();
	}
	
	public void afficherDetailsEpreuve() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ? ");
		long identifiant = scanner.nextLong();
		EpreuveFullDto epreuve = epreuveService.getEpreuveDetaillee(identifiant);
		System.out.println("Le nom du tournoi est " + epreuve.getTournoi().getNom());
		
		for(JoueurDto joueurDto : epreuve.getParticipants()) {
			System.out.println(joueurDto.getPrenom() + " " + joueurDto.getNom());
		}
	}
	
	public void afficherRolandGarros() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ? ");
		long identifiant = scanner.nextLong();
		EpreuveLightDto epreuve = epreuveService.getEpreuveSansTournoi(identifiant);		
	}
	
	public void afficheListEpreuves() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est le code du tournoi ? ");
		String code = scanner.nextLine();
		for (EpreuveFullDto dto : epreuveService.getListeEpreuves(code)) {
			System.out.println(dto.getId() + " " + dto.getAnnee() + " " + dto.getTypeEpreuve() + " " + dto.getTournoi().getNom());
		}
	}
}
