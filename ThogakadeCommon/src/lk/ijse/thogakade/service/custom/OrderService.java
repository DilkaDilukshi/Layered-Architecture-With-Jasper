/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.service.SuperService;

/**
 *
 * @author Dil
 */
public interface OrderService extends SuperService<OrderDTO>{

    
    @Override
    public default boolean add(OrderDTO dto) throws RemoteException{return false;};
    
    public boolean add(OrderDTO dto,ArrayList<OrderDetailDTO> arList) throws RemoteException;
    
}
