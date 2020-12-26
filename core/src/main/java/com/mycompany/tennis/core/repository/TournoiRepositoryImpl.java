package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Tournoi;

public class TournoiRepositoryImpl {

	public void create(Tournoi tournoi) {			
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        //session.persist(tournoi);  
		em.persist(tournoi);  
        System.out.println("Tournoi ajouté");                   
    }
	
	public void update(Tournoi tournoi) {
		Connection conn = null;
		try {            
			DataSource data = DataSourceProvider.getSingleDataSourceInstance();
        	
        	conn = data.getConnection();
        	                                   
            PreparedStatement state = conn.prepareStatement("update tournoi set NOM=?, CODE=? where id=?");            
                      
            state.setString(1, tournoi.getNom());
            state.setString(2, tournoi.getCode());        
            state.setLong(3, tournoi.getId());                      
            state.executeUpdate();
                                                         
            System.out.println("Tournoi modifié");
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
		//Tournoi tournoi = new Tournoi();
		//tournoi.setId(id);
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();  
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Tournoi tournoi = em.find(Tournoi.class, id);
		//session.delete(tournoi);	
		em.remove(tournoi);
        System.out.println("Tournoi supprimé");              
    }
	
	public Tournoi getById(Long id) {		
		Tournoi tournoi = null;
		           
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        //tournoi = session.get(Tournoi.class, id);   
		tournoi = em.find(Tournoi.class, id);
        System.out.println("Tournoi lu");
		
		return tournoi;
    }
	
	public List<Tournoi> list() {
		Connection conn = null;
		List<Tournoi> tournois = new ArrayList<>();
		
		try {            
			DataSource data = DataSourceProvider.getSingleDataSourceInstance();
        	
        	conn = data.getConnection();
        	                                   
            PreparedStatement state = conn.prepareStatement("select id, nom, code from tournoi");            
                                                                              
            ResultSet rs = state.executeQuery();
            
            while (rs.next()) {
            	Tournoi tournoi = new Tournoi();
            	tournoi.setId(rs.getLong("id"));
            	tournoi.setNom(rs.getString("nom"));
            	tournoi.setCode(rs.getString("code"));            	          	
            	tournois.add(tournoi);
            }
                                                         
            System.out.println("Tournois lus");
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
		return tournois;
    }
}
