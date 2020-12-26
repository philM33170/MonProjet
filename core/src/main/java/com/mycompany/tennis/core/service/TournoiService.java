package com.mycompany.tennis.core.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {

	private TournoiRepositoryImpl tournoiRepository;
	
	public TournoiService() {
		this.tournoiRepository = new TournoiRepositoryImpl();
	}
	
	@SuppressWarnings("static-access")
	public void createTournoi (TournoiDto dto) {
		//Session session = null;	
		EntityManager em = null;
		//Transaction tx = null;
		EntityTransaction tx = null;
		
		try {
			//session = HibernateUtil.getSessionFactory().getCurrentSession();
			em = new EntityManagerHolder().getCurrentEntityManager();
			//tx = session.beginTransaction();
			tx = em.getTransaction();
			tx.begin();
			Tournoi tournoi = new Tournoi();
			tournoi.setId(dto.getId());
			tournoi.setCode(dto.getCode());
			tournoi.setNom(dto.getNom());
			tournoiRepository.create(tournoi);
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
	}
	
	@SuppressWarnings("static-access")
	public TournoiDto getTournoi (Long id) {
		//Session session = null;	
		EntityManager em = null;
		//Transaction tx = null;
		EntityTransaction tx = null;
		Tournoi tournoi = null;
		TournoiDto dto = null;
		try {
			
			//session = HibernateUtil.getSessionFactory().getCurrentSession();
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			tournoi = tournoiRepository.getById(id);
			dto = new TournoiDto();
			dto.setId(tournoi.getId());
			dto.setCode(tournoi.getCode());
			dto.setNom(tournoi.getNom());
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
	public void deleteTournoi(Long id) {
		//Session session = null;
		EntityManager em = null;
		//Transaction tx = null;
		EntityTransaction tx = null;
		
		try {
			//session = HibernateUtil.getSessionFactory().getCurrentSession();
			em = new EntityManagerHolder().getCurrentEntityManager();
			//tx = session.beginTransaction();
			tx = em.getTransaction();
			tx.begin();
			tournoiRepository.delete(id);
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
	}
}
