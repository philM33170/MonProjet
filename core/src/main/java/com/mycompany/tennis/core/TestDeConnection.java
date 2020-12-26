package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;
import com.mycompany.tennis.core.service.TournoiService;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;

public class TestDeConnection {
    public static void main(String... args){
        
    	//JoueurService joueurService = new JoueurService();
    	//Joueur noah = new Joueur();
    	//noah.setNom("Noah");
    	//noah.setPrenom("Yannick");
    	//noah.setSexe('H');
    	//joueurService.createJoueur(noah);
    	//System.out.println("L'identifiant du joueur créé est " + noah.getId());
    	//System.out.println(joueurService.getJoueur(noah).getPrenom() + " " + joueurService.getJoueur(noah).getNom());
    	
    	//TournoiService tournoiService = new TournoiService();
    	//Tournoi bercy = new Tournoi();
    	//bercy.setNom("Bercy");
    	//bercy.setCode("BE");
    	//tournoiService.createTournoi(bercy);
    	//System.out.println("L'identifiant du tournoi créé est " + bercy.getId());
    	//tournoiService.getTournoi(bercy);
    	
    	MatchService matchService = new MatchService();
    	Match match = new Match();
    	Score score = new Score();
    	score.setSet1((byte) 3);
    	score.setSet2((byte) 4);
    	score.setSet3((byte) 6);
    	match.setScore(score);
    	score.setMatch(match);
    	Joueur federer = new Joueur();
    	federer.setId(32L);
    	Joueur murray = new Joueur();
    	murray.setId(34L);
    	match.setVainqueur(federer);
    	match.setFinaliste(murray);
    	Epreuve epreuve = new Epreuve();
    	epreuve.setId(10L);
    	match.setEpreuve(epreuve);
    	
    	matchService.enregistrerNouveauMatch(match);
    	System.out.println("L'identifiant du match créé est " + match.getId());    		    	
    }
}
