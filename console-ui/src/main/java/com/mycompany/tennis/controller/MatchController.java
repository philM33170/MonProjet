package com.mycompany.tennis.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.service.MatchService;

public class MatchController {

	private MatchService matchService;
	
	public MatchController() {
		this.matchService = new MatchService();
	}
	
	public void supprimerMatch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du match que vous voulez supprimer ?");
		Long identifiant = scanner.nextLong();
		matchService.deleteMatch(identifiant);
	}
	
	public void tapisVert() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du match que vous voulez annuler ?");
		Long identifiant = scanner.nextLong();
		matchService.tapisVert(identifiant);
	}
	
	public void ajouterMatch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant de l'épreuve ?");
		Long epreuveId = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Quel est l'identifiant du vainqueur ?");
		Long vainqueurId = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Quel est l'identifiant du finaliste ?");
		Long finalisteId = scanner.nextLong();
		scanner.nextLine();
		
		MatchDto dto = new MatchDto();
		dto.setEpreuve(new EpreuveFullDto());
		dto.getEpreuve().setId(epreuveId);
		dto.setFinaliste(new JoueurDto());
		dto.getFinaliste().setId(finalisteId);
		dto.setVainqueur(new JoueurDto());
		dto.getVainqueur().setId(vainqueurId);
		
		System.out.println("Quel est la valeur du 1er set ?");
		Byte set1 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 2ème set ?");
		Byte set2 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 3ème set ?");
		Byte set3 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 4ème set ?");
		Byte set4 = scanner.nextByte();
		scanner.nextLine();
		System.out.println("Quel est la valeur du 5ème set ?");
		Byte set5 = scanner.nextByte();
		scanner.nextLine();
		
		ScoreFullDto scoreDto = new ScoreFullDto();
		scoreDto.setSet1(set1);
		scoreDto.setSet2(set2);
		scoreDto.setSet3(set3);
		scoreDto.setSet4(set4);
		scoreDto.setSet5(set5);
		
		dto.setScore(scoreDto);
		scoreDto.setMatch(dto);
		
		matchService.createMatch(dto);
		
	}
	
	public void afficheDetailsMatch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du match que vous souhaité consulter ?");
		Long identifiant = scanner.nextLong();
		MatchDto match = matchService.getMatch(identifiant);
		System.out.println("Il s'agit d'un match de " + match.getEpreuve().getAnnee() + " qui s'est déroulé à " + match.getEpreuve().getTournoi().getNom());
		System.out.println("Le match sélectionné a eu comme vainqueur " + match.getVainqueur().getPrenom() + " " + match.getVainqueur().getNom() + " vs " + match.getFinaliste().getPrenom() + " " + match.getFinaliste().getNom());	
		System.out.print("Le score est : ");
		System.out.print(match.getScore().getSet1() + " ");
		System.out.print(match.getScore().getSet2()  + " ");
		if (match.getScore().getSet3()  != null) {
			System.out.print(match.getScore().getSet3()  + " ");
		}
		if (match.getScore().getSet4()  != null) {
			System.out.print(match.getScore().getSet4()  + " ");
		}
		if (match.getScore().getSet5()  != null) {
			System.out.print(match.getScore().getSet5()  + " ");
		}	
	}
}
