/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao.custom.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dao.custom.CustomerDAO;
import lk.ijse.thogakade.dto.CustomerDTO;

/**
 *
 * @author Dil
 */

public class CustomerDAOImpl implements CustomerDAO{
    private Connection connection=null;
    
    private final String TABLE_NAME = "Customer";
    
    @Override
    public void setConnection(Connection connection) {
        this.connection=connection;
        
    }
    

//    @Override
//    public boolean add(CustomerDTO customer)throws SQLException{
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?)");
//        pst.setObject(1, customer.getId());
//        pst.setObject(2, customer.getName());
//        pst.setObject(3, customer.getAddress());
//        pst.setObject(4, customer.getSalary());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

    

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException {
        ArrayList<CustomerDTO> alCustomers = new ArrayList<>();
        String sql = "select * from Customer";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            String id = rset.getString(1);
            String name = rset.getString(2);
            String address = rset.getString(3);
            Double salary = rset.getDouble(4);

            CustomerDTO customer = new CustomerDTO(id, name, address, salary);
            alCustomers.add(customer);
        }
        return alCustomers;
    }

//    @Override
//    public CustomerDTO get(CustomerDTO t) throws SQLException {
//        String id=t.getId();
//        CustomerDTO customer = null;
//        String sql = "SELECT * FROM Customer WHERE id = '" + id + "' ";
//        Statement stm = connection.createStatement();
//        ResultSet rset = stm.executeQuery(sql);
//        if (rset.next()) {
//            customer = new CustomerDTO(
//                    rset.getString(1),
//                    rset.getString(2),
//                    rset.getString(3),
//                    rset.getDouble(4));
//        }
//        return customer;    
//    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    

    
    
}
