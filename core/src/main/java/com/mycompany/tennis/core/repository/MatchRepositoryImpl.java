package com.mycompany.tennis.core.repository;

import javax.persistence.EntityManager;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Match;

public class MatchRepositoryImpl {

	public Match getById(Long id) {		
		Match match = null;
		          
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		match = em.find(Match.class, id);                                         
        System.out.println("Match lu");
		
		return match;
    }
	
	public void create(Match match) {	
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();  
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        em.persist(match);                                                                             
        System.out.println("Match ajouté");
    }
	
	public void delete(Long id) {		
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();   
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Match match = em.find(Match.class, id);
		em.remove(match);		
        System.out.println("Match supprimé");              
    }
}
