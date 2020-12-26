package com.mycompany.tennis.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {

	private JoueurRepositoryImpl joueurRepository;
	
	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}
		
	@SuppressWarnings("static-access")
	public List<JoueurDto> getListeJoueurs(char sexe) {
		EntityManager em = null;
		EntityTransaction tx = null;
		List<JoueurDto> dtos = new ArrayList<>();
		
		try {				
			em = new EntityManagerHolder().getCurrentEntityManager();			
			tx = em.getTransaction();
			tx.begin();
			List<Joueur> joueurs = joueurRepository.list(sexe);
			
			for(Joueur joueur : joueurs) {
				final JoueurDto dto = new JoueurDto();
				dto.setId(joueur.getId());
				dto.setNom(joueur.getNom());
				dto.setPrenom(joueur.getPrenom());
				dto.setSexe(joueur.getSexe());
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
        	if (em != null) {
				em.close();
        	}
        }
		return dtos;
	}
	
	@SuppressWarnings("static-access")
	public void createJoueur(Joueur joueur) {	
		EntityManager em = null;
		EntityTransaction tx = null;	
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			joueurRepository.create(joueur);			
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
	public Joueur getJoueur (Long id) {		
		EntityManager em = null;
		EntityTransaction tx = null;
		Joueur joueur = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			joueur = joueurRepository.getById(id);			
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
		return joueur;
	}
	
	@SuppressWarnings("static-access")
	public void renomme (Long id, String nouveauNom) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Joueur joueur = joueurRepository.getById(id);
			joueur.setNom(nouveauNom);
			tx.commit();
			System.out.println("Nom du joueur modifié");
			
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
	public void changementSexe (Long id, char nouveauSexe) {
		EntityManager em = null;	
		EntityTransaction tx = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Joueur joueur = joueurRepository.getById(id);
			joueur.setSexe(nouveauSexe);
			tx.commit();
			System.out.println("Sexe du joueur modifié");
			
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
	public void deleteJoueur(Long id) {
		EntityManager em = null;	
		EntityTransaction tx = null;
		
		try {
			em = new EntityManagerHolder().getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			joueurRepository.delete(id);
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
}
