package com.mycompany.tennis.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;

public class MatchDaoImpl {

	public void createMatchWithScore (Match match) {
		Connection conn = null;
		try {            
        	DataSource data = DataSourceProvider.getSingleDataSourceInstance();
        	       	
        	conn = data.getConnection();
        	
        	conn.setAutoCommit(false);
        	                                   
            PreparedStatement state = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);            
                      
            state.setLong(1, match.getEpreuve().getId());
            state.setLong(2, match.getVainqueur().getId());
            state.setLong(3, match.getFinaliste().getId());
                                
            state.executeUpdate();
            
            ResultSet rs = state.getGeneratedKeys();
            
            if (rs.next()) {
            	match.setId(rs.getLong(1));
            }
                                                         
            System.out.println("Match créé");
            
            PreparedStatement state1 = conn.prepareStatement("insert into score_vainqueur (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);            
            
            state1.setLong(1, match.getScore().getMatch().getId());
            state1.setByte(2, match.getScore().getSet1());
            state1.setByte(3, match.getScore().getSet2());
            if (match.getScore().getSet3() == null) {
            	state1.setNull(4, Types.TINYINT);
            } else {
            	state1.setByte(4, match.getScore().getSet3());
            }
            if (match.getScore().getSet4() == null) {
            	state1.setNull(5, Types.TINYINT);
            } else {
            	state1.setByte(5, match.getScore().getSet4());
            }
            if (match.getScore().getSet5() == null) {
            	state1.setNull(6, Types.TINYINT);
            } else {
            	state1.setByte(6, match.getScore().getSet5());
            }
            
            state1.executeUpdate();
            
            ResultSet rs1 = state1.getGeneratedKeys();
            
            if (rs1.next()) {
            	match.getScore().setId(rs1.getLong(1));
            }
                                                         
            System.out.println("Score créé");
            
            conn.commit();
            
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
	
}
