/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.business.SuperBO;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;

/**
 *
 * @author Dil
 */
public interface OrderBO extends SuperBO<OrderDTO>{
    public boolean add(OrderDTO dto,ArrayList<OrderDetailDTO> arList) throws Exception;
    @Override
    public default boolean add(OrderDTO dto){return false;}

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public default ArrayList<OrderDTO> getAll() throws SQLException {return null;}
    
}
