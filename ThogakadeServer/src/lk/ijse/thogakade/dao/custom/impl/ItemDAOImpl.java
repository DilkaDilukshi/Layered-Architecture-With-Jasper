/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dao.custom.ItemDAO;
import lk.ijse.thogakade.dto.ItemDTO;

/**
 *
 * @author Dil
 */
public class ItemDAOImpl implements ItemDAO{
    private Connection connection=null;
    private final String TABLE_NAME = "Item";
    
    @Override
    public void setConnection(Connection connection) {
        this.connection=connection;
        
    }

//    @Override
//    public boolean add(ItemDTO dto) throws SQLException {
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
//        pst.setObject(1, dto.getCode());
//        pst.setObject(2, dto.getDescription());
//        pst.setObject(3, dto.getUnitPrice());
//        pst.setObject(4, dto.getQtyOnHand());
//        int result = pst.executeUpdate();
//        return (result > 0);
//        
//    }

//    @Override
//    public boolean update(ItemDTO dto) throws SQLException {
//        PreparedStatement pst = connection.prepareStatement("update Item set description=?,unitPrice=?,QtyOnHand=? where code=? ");
//        pst.setObject(4, dto.getCode());
//        pst.setObject(1, dto.getDescription());
//        pst.setObject(2, dto.getUnitPrice());
//        pst.setObject(3, dto.getQtyOnHand());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

//    @Override
//    public boolean delete(ItemDTO t) throws SQLException {
//        String id=t.getCode();
//        String sql = "DELETE FROM Item WHERE code = ? ";
//        PreparedStatement pst = connection.prepareStatement(sql);
//        pst.setObject(1, id);
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

    
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException {
        ArrayList<ItemDTO> alItem = new ArrayList<>();
        String sql = "select * from Item";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            String id = rset.getString(1);
            String description = rset.getString(2);
            double uniP = rset.getDouble(3);
            int qty = rset.getInt(4);

            ItemDTO item = new ItemDTO(id, description, uniP, qty);
            alItem.add(item);
        }
        
        return alItem;
    }

    @Override
    public ItemDTO get(ItemDTO t) throws SQLException {
        String id=t.getCode();
        ItemDTO item = null;
        String sql = "SELECT * FROM Item WHERE code = '" + id + "' ";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        if (rset.next()) {
            item=new ItemDTO(id,rset.getString(2) , rset.getDouble(3), rset.getInt(4));
            //public ItemDTO(String code, String description, double unitPrice, Integer qtyOnHand) {
    
        }
        return item;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    
}
