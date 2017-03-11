/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.business.custom.OrderBO;
import lk.ijse.thogakade.dao.*;
import lk.ijse.thogakade.dto.*;

/**
 *
 * @author Dil
 */

public class OrderBOImpl implements OrderBO{
    private final SuperDAO daoI;
    private final SuperDAO daoO;
    private final SuperDAO daoOD;
        
    public OrderBOImpl() throws Exception {
                
        daoI = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        daoO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        daoOD = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
        
    }
    

    @Override
    public boolean add(OrderDTO dto,ArrayList<OrderDetailDTO> arList) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        daoI.setConnection(connection);
        daoO.setConnection(connection);
        daoOD.setConnection(connection);
        boolean result=daoO.add(dto);
        if(!result){
            
            connection.close();
            return result;
        }
        for(OrderDetailDTO odto:arList){
            
            result=result && daoOD.add( odto);
            if(!result) {
                connection.rollback();
                connection.setAutoCommit(result);
                connection.close();
                return result;
            }
            ItemDTO itemDTO=new ItemDTO(odto.getItemCode());
            itemDTO = (ItemDTO) daoI.get(itemDTO);
            int qty=itemDTO.getQtyOnHand()-odto.getQty();
            itemDTO.setQtyOnHand(qty);
            result=result && daoI.update(itemDTO);
            if(!result){
                connection.rollback();
                connection.setAutoCommit(result);
                connection.close();
                return result;
            }           
            
        }
        connection.commit();
        connection.setAutoCommit(result);
        connection.close();
        return result;
        
        
    }

    @Override
    public OrderDTO get(OrderDTO dto) throws Exception {

        Connection connection = ConnectionFactory.getInstance().getConnection();
        daoO.setConnection(connection);
        
        int a=daoO.getAll().size()+1;
        if(a<10){
            dto.setId("D00"+a);
        }else if(a<100){
            dto.setId("D0"+a);
        }else{
            dto.setId("D"+a);
        }
        connection.close();
        return dto;
    }

    

    @Override
    public boolean update(OrderDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(OrderDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    
    
}
