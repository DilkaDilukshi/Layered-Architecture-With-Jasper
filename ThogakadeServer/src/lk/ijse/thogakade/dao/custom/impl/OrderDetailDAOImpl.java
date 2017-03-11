/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dao.ConnectionFactory;
import lk.ijse.thogakade.dao.custom.OrderDetailDAO;
import lk.ijse.thogakade.dto.OrderDetailDTO;

/**
 *
 * @author Dil
 */
public class OrderDetailDAOImpl implements OrderDetailDAO {

    private Connection connection = null;
    private final String TABLE_NAME = "OrderDetail";
    
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;

    }

//    @Override
//    public boolean add(OrderDetailDAO t) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    @Override
//    public boolean update(OrderDetailDTO dto) throws SQLException {
//        PreparedStatement pst = connection.prepareStatement("update orderDetail set ItemCode=?,qty=?,unitPrice=? where OrderId=? ");
//        pst.setObject(4, dto.getOrderId());
//        pst.setObject(1, dto.getItemCode());
//        pst.setObject(2, dto.getQty());
//        pst.setObject(3, dto.getUnitPrice());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }
//
//    @Override
//    public boolean delete(OrderDetailDTO t) throws SQLException {
//        String id = t.getOrderId();
//        String sql = "DELETE FROM OrderDetail WHERE id = ? ";
//        PreparedStatement pst = connection.prepareStatement(sql);
//        pst.setObject(1, id);
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

    @Override
    public OrderDetailDTO get(OrderDetailDTO t) throws SQLException {
        String id = t.getOrderId();
        
        OrderDetailDTO orderDe = null;
        String sql = "SELECT * FROM orderDetails WHERE id = '" + id + "' ";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        if (rset.next()) {
            orderDe=new OrderDetailDTO(
                    rset.getString(1),
                    rset.getString(2),
                    rset.getInt(3),
                    rset.getDouble(4)
            );
        }
        return orderDe;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ArrayList<OrderDetailDTO> alOrderDetails = new ArrayList<>();
        String sql = "select * from orderDetail";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            OrderDetailDTO orderDetail = new OrderDetailDTO(
                    rset.getString(1),
                    rset.getString(2),
                    rset.getInt(3),
                    rset.getDouble(4)
            );
            alOrderDetails.add(orderDetail);
        }

        return alOrderDetails;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
