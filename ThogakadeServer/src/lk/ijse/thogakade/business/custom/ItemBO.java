/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.business.SuperBO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.dto.SuperDTO;

/**
 *
 * @author Dil
 */
public interface ItemBO extends SuperBO<ItemDTO>{
    public default boolean add(ItemDTO dto,ArrayList<OrderDetailDTO> arList) 
            throws Exception{return true;}
    
    
}
