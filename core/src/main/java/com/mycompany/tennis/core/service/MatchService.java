package com.mycompany.tennis.core.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

	private ScoreRepositoryImpl scoreRepository;
	private MatchRepositoryImpl matchRepository;
	private EpreuveRepositoryImpl epreuveRepository;
	private JoueurRepositoryImpl joueurRepository;
		
	public MatchService() {
		this.scoreRepository = new ScoreRepositoryImpl();
		this.matchRepository = new MatchRepositoryImpl();
		this.epreuveRepository = new EpreuveRepositoryImpl();
		this.joueurRepository = new JoueurRepositoryImpl();
	}
	
	public void enregistrerNouveauMatch(Match match) {
		matchRepository.create(match);
		scoreRepository.create(match.getScore());		
	}
	
	@SuppressWarnings("static-access")
	public void deleteMatch(Long id) {
		EntityManager em = null;	
		EntityTransaction tx = null;	
			
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			matchRepository.delete(id);
			
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {            
			if (em != null) {
				em.close();
        	}
        }
	}
	
	@SuppressWarnings("static-access")
	public void createMatch(MatchDto dto) {
		EntityManager em = null;
		EntityTransaction tx = null;	
		Match match = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			match = new Match();
			match.setEpreuve(epreuveRepository.getById(dto.getEpreuve().getId()));
			match.setVainqueur(joueurRepository.getById(dto.getVainqueur().getId()));
			match.setFinaliste(joueurRepository.getById(dto.getFinaliste().getId()));
			Score score = new Score();
			score.setMatch(match);
			match.setScore(score);
			score.setSet1(dto.getScore().getSet1());
			score.setSet2(dto.getScore().getSet2());
			score.setSet3(dto.getScore().getSet3());
			score.setSet4(dto.getScore().getSet4());
			score.setSet5(dto.getScore().getSet5());
			
			matchRepository.create(match);
			
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {            
			if (em != null) {
				em.close();
        	}
        }
	}
	
	@SuppressWarnings("static-access")
	public void tapisVert(Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;			
		Match match = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			match = matchRepository.getById(id);
			
			Joueur ancienVainqueur = match.getVainqueur();
			match.setVainqueur(match.getFinaliste());
			match.setFinaliste(ancienVainqueur);
			
			match.getScore().setSet1((byte)0);
			match.getScore().setSet2((byte)0);
			match.getScore().setSet3((byte)0);
			match.getScore().setSet4((byte)0);
			match.getScore().setSet5((byte)0);
			
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {            
			if (em != null) {
				em.close();
        	}
        }
	}
		
	@SuppressWarnings("static-access")
	public MatchDto getMatch(Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		MatchDto dto = null;
		Match match = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			match = matchRepository.getById(id);
			
			dto = new MatchDto();
			dto.setId(match.getId());
			JoueurDto finalisteDto = new JoueurDto();
			finalisteDto.setId(match.getFinaliste().getId());
			finalisteDto.setNom(match.getFinaliste().getNom());
			finalisteDto.setPrenom(match.getFinaliste().getPrenom());
			finalisteDto.setSexe(match.getFinaliste().getSexe());
			dto.setFinaliste(finalisteDto);
			JoueurDto vainqueurDto = new JoueurDto();
			vainqueurDto.setId(match.getVainqueur().getId());
			vainqueurDto.setNom(match.getVainqueur().getNom());
			vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
			vainqueurDto.setSexe(match.getVainqueur().getSexe());			
			dto.setVainqueur(vainqueurDto);
			
			EpreuveFullDto epreuveDto = new EpreuveFullDto();
			epreuveDto.setId(match.getEpreuve().getId());
			epreuveDto.setAnnee(match.getEpreuve().getAnnee());
			epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(match.getEpreuve().getTournoi().getId());
			tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
			tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
			epreuveDto.setTournoi(tournoiDto);
			
			dto.setEpreuve(epreuveDto);
			
			ScoreFullDto scoreDto = new ScoreFullDto();
			scoreDto.setId(match.getScore().getId());
			scoreDto.setSet1(match.getScore().getSet1());
			scoreDto.setSet2(match.getScore().getSet2());
			scoreDto.setSet3(match.getScore().getSet3());
			scoreDto.setSet4(match.getScore().getSet4());
			scoreDto.setSet5(match.getScore().getSet5());
			
			dto.setScore(scoreDto);
			scoreDto.setMatch(dto);
			
			tx.commit();
								
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {            
			if (em != null) {
				em.close();
        	}
        }
		return dto;
	}
}
