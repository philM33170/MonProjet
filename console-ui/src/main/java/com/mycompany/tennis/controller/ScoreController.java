package com.mycompany.tennis.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.service.ScoreService;

public class ScoreController {

	private ScoreService scoreService;
	
	public ScoreController() {
		this.scoreService = new ScoreService();
	}
	
	public void supprimerScore() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du score que vous voulez supprimer ?");
		Long identifiant = scanner.nextLong();
		scoreService.deleteScore(identifiant);
	}
	
	public void afficheDetailsScore() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est l'identifiant du score que vous souhaitez consulter ?");
		long identifiant = scanner.nextLong();
		ScoreFullDto score = scoreService.getScore(identifiant);	
		System.out.println("Le tournoi " + score.getMatch().getEpreuve().getTournoi().getNom() + " s'est déroulé en " + score.getMatch().getEpreuve().getAnnee());
		System.out.println("C'était un match " + score.getMatch().getEpreuve().getTypeEpreuve());
		System.out.print("Le score est : ");
		System.out.print(score.getSet1() + " ");
		System.out.print(score.getSet2() + " ");
		if (score.getSet3() != null) {
			System.out.print(score.getSet3() + " ");
		}
		if (score.getSet4() != null) {
			System.out.print(score.getSet4() + " ");
		}
		if (score.getSet5() != null) {
			System.out.print(score.getSet5() + " ");
		}				
	}
}
