/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.thogakade.dao.db.DBConnection;

/**
 *
 * @author Dil
 */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private Connection connection; 
    private ConnectionFactory() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            //logger එක බලන්න
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    public static ConnectionFactory getInstance() throws SQLException{
        if(connectionFactory==null){
            connectionFactory=new ConnectionFactory();
        }
        return connectionFactory;
    }
    
    public Connection getConnection() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost/Company" , "root" , "ijse");
        return connection;
    }
    
}
