package com.mycompany.tennis.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;

public class EpreuveRepositoryImpl {

	public Epreuve getById(Long id) {		
		Epreuve epreuve = null;
		          
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		//epreuve = session.get(Epreuve.class, id);    
		epreuve = em.find(Epreuve.class, id);
        System.out.println("Epreuve lue");
		
		return epreuve;
    }
	
	public List<Epreuve> list(String codeTournoi) {		
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		//Query<Epreuve> query = em.createQuery("select e from Epreuve e where e.tournoi.code=?0", Epreuve.class);
		TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e where e.tournoi.code=?0", Epreuve.class);
		query.setParameter(0, codeTournoi);
		List<Epreuve> epreuve = query.getResultList();						                                                        
        System.out.println("Epreuves lus");       
		return epreuve;
    }
}
