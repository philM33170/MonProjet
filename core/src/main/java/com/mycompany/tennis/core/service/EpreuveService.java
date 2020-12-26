package com.mycompany.tennis.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;

public class EpreuveService {

	private EpreuveRepositoryImpl epreuveRepository;
	
	public EpreuveService() {
		this.epreuveRepository = new EpreuveRepositoryImpl();
	}
	
	@SuppressWarnings("static-access")
	public EpreuveFullDto getEpreuveDetaillee (Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Epreuve epreuve = null;
		EpreuveFullDto dto = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();			
			epreuve = epreuveRepository.getById(id);			
			//Hibernate.initialize(epreuve.getTournoi());			
			
			dto = new EpreuveFullDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(epreuve.getTournoi().getId());
			tournoiDto.setNom(epreuve.getTournoi().getNom());
			tournoiDto.setCode(epreuve.getTournoi().getCode());
			dto.setTournoi(tournoiDto);
			
			dto.setParticipants(new HashSet<JoueurDto>());
			
			for(Joueur joueur : epreuve.getParticipants()) {
				final JoueurDto joueurDto = new JoueurDto();
				joueurDto.setId(joueur.getId());
				joueurDto.setNom(joueur.getNom());
				joueurDto.setPrenom(joueur.getPrenom());
				joueurDto.setSexe(joueur.getSexe());
				dto.getParticipants().add(joueurDto);				
			}
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {            
			//if (session != null) {
        	//	session.close();
			if (em != null) {
				em.close();
        	}
        }
		return dto;
	}
	
	@SuppressWarnings("static-access")
	public EpreuveLightDto getEpreuveSansTournoi (Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Epreuve epreuve = null;
		EpreuveLightDto dto = null;
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();	
			epreuve = epreuveRepository.getById(id);			
			
			dto = new EpreuveLightDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
			tx.commit();
						
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {            
			//if (session != null) {
        	//	session.close();
			if (em != null) {
				em.close();
        	}
        }
		return dto;
	}
	
	@SuppressWarnings("static-access")
	public List<EpreuveFullDto> getListeEpreuves(String codeTournoi) {
		//Session session = null;
		EntityManager em = null;
		//Transaction tx = null;
		EntityTransaction tx = null;
		List<EpreuveFullDto> dtos = new ArrayList<>();
		
		try {
			
			//session = HibernateUtil.getSessionFactory().getCurrentSession();
			em = new EntityManagerHolder().getCurrentEntityManager();
			//tx = session.beginTransaction();
			tx = em.getTransaction();
			tx.begin();
			List<Epreuve> epreuves = epreuveRepository.list(codeTournoi);
			
			for(Epreuve epreuve : epreuves) {
				final EpreuveFullDto dto = new EpreuveFullDto();
				dto.setId(epreuve.getId());
				dto.setAnnee(epreuve.getAnnee());
				dto.setTypeEpreuve(epreuve.getTypeEpreuve());
				TournoiDto tournoiDto = new TournoiDto();
				tournoiDto.setId(epreuve.getTournoi().getId());
				tournoiDto.setNom(epreuve.getTournoi().getNom());
				tournoiDto.setCode(epreuve.getTournoi().getCode());
				dto.setTournoi(tournoiDto);
				dtos.add(dto);
			}
			
			tx.commit();			
			
		} catch (Exception e) {
        	if (tx != null) {
        		tx.rollback();
        	}
			e.printStackTrace();
		}
        finally {            
        	//if (session != null) {
        	//	session.close();
        	if (em != null) {
				em.close();
        	}
        }
		return dtos;
	}
}
