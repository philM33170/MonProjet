package com.mycompany.tennis.core.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class ScoreService {

	private ScoreRepositoryImpl scoreRepository;
	
	public ScoreService() {
		this.scoreRepository = new ScoreRepositoryImpl();
	}
	
	@SuppressWarnings("static-access")
	public void deleteScore(Long id) {
		EntityManager em = null;	
		EntityTransaction tx = null;	
				
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			scoreRepository.delete(id);
			
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
	public ScoreFullDto getScore(Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Score score = null;
		ScoreFullDto dto = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			score = scoreRepository.getById(id);
			
			dto = new ScoreFullDto();
			dto.setId(score.getId());			
			dto.setSet1(score.getSet1());
			dto.setSet2(score.getSet2());
			dto.setSet3(score.getSet3());
			dto.setSet4(score.getSet4());
			dto.setSet5(score.getSet5());	
			
			MatchDto matchDto = new MatchDto();
			matchDto.setId(score.getMatch().getId());
			dto.setMatch(matchDto);
			
			EpreuveFullDto epreuveDto = new EpreuveFullDto();
			epreuveDto.setId(score.getMatch().getEpreuve().getId());
			epreuveDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
			epreuveDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
			tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
			tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
			epreuveDto.setTournoi(tournoiDto);
			
			matchDto.setEpreuve(epreuveDto);
									
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
