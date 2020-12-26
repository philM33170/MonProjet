package com.mycompany.tennis;

import java.util.Scanner;

import com.mycompany.tennis.controller.EpreuveController;
import com.mycompany.tennis.controller.JoueurController;
import com.mycompany.tennis.controller.MatchController;
import com.mycompany.tennis.controller.ScoreController;
import com.mycompany.tennis.controller.TournoiController;

public class UI {

	public static void main(String...args) {
		JoueurController controller = new JoueurController();
		controller.afficheListeJoueurs();
		//controller.afficheDetailsJoueur();
		//controller.creerJoueur();
		//TournoiController controller2 = new TournoiController();
		//controller2.afficherDetailsTournoi();
		//controller2.creerTournoi();
		//controller2.deleteTournoi();
		//controller.renommeJoueur();h
		//controller.changerSexeJoueur();
		//controller.supprimeJoueur();
		//controller2.deleteTournoi();
		//ScoreController controller3 = new ScoreController();
		//controller3.supprimerScore();
		//EpreuveController controller4 = new EpreuveController();
		//controller4.afficheListEpreuves();
		//MatchController controller5 = new MatchController();
		//controller5.ajouterMatch();	
	}
}
