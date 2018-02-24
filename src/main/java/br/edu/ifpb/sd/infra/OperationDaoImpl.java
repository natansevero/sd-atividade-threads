/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sd.infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class OperationDaoImpl implements OperationDao {

    private Connection conn;
    
    public OperationDaoImpl() {
        try {
            this.conn = ConnFactory.getConnection("jdbc:postgresql://127.0.0.1:5432/AtividadeThreads", "postgres", "12345");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperationDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(int id, String name) {
        String sql = "insert into tabela (id, name, edited, deleted) values (?, ?, FALSE, FALSE)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            
            int execute;
            execute = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int id) {
        String sql = "update tabela set edited = true where id = ?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            int execute;
            execute = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "update tabela set deleted = true where id = ?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            int execute;
            execute = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int selectLastId() {
        String sql = "select id from tabela order by id desc limit 1";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            
            return rs.getInt(1);
            
        } catch(SQLException ex) {
//            Logger.getLogger(OperationDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
}
