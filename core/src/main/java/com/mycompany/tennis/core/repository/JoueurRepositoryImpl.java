package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Joueur;

public class JoueurRepositoryImpl {
		
	public void create(Joueur joueur) {			
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		em.persist(joueur); 
        System.out.println("Joueur créé");                  
    }
	
	public void update(Joueur joueur) {
		Connection conn = null;
		try {            
			DataSource data = DataSourceProvider.getSingleDataSourceInstance();
        	
        	conn = data.getConnection();
        	                                   
            PreparedStatement state = conn.prepareStatement("update joueur set NOM=?, PRENOM=?, sexe=? where id=?");            
                      
            state.setString(1, joueur.getNom());
            state.setString(2, joueur.getPrenom());
            state.setString(3, joueur.getSexe().toString());
            state.setLong(4, joueur.getId());                      
            state.executeUpdate();
                                                         
            System.out.println("Joueur modifié");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
            	if (conn != null) {
            		conn.rollback();
            	}
            } catch (SQLException e1) {
            	e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public void delete(Long id) {
		Joueur joueur = getById(id);
		  
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		joueur = em.find(Joueur.class, id);
		em.remove(joueur);		
        System.out.println("Joueur supprimé");                 
    }
	
	public Joueur getById(Long id) {				
		Joueur joueur = null;
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		joueur = em.find(Joueur.class, id);
        System.out.println("Joueur lu");
        
		return joueur;
    }
	
	public List<Joueur> list(char sexe) {			
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
		query.setParameter(0, sexe);
		List<Joueur> joueurs = query.getResultList();						                                                        
        System.out.println("Joueurs lus");       
		return joueurs;
    }
}


