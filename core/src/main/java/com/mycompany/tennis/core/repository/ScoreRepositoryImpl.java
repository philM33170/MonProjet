package com.mycompany.tennis.core.repository;

import javax.persistence.EntityManager;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Score;

public class ScoreRepositoryImpl {

	public void create(Score score) {			
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		em.persist(score);                                                                              
		System.out.println("Score ajouté");
    }
	
	public Score getById(Long id) {
		Score score = null;
		
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		score = em.find(Score.class, id);
		System.out.println("Score lu");
		
		return score; 
	}
	
	public void delete(Long id) {		
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();   
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Score score = em.find(Score.class, id);
		em.remove(score);		
        System.out.println("Score supprimé");              
    }
}
