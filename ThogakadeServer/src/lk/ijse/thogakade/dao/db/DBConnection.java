
package lk.ijse.thogakade.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    
    private static DBConnection dbconnection;
    private Connection connection;

    public DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost/Company" , "root" , "ijse");
        
    }
    
    public static DBConnection getInstance() throws SQLException{
        if(dbconnection == null){
            dbconnection = new DBConnection();
        }
        return dbconnection;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    
    
}
