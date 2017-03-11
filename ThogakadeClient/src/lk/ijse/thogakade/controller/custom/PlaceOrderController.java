/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.controller.SuperController;
import lk.ijse.thogakade.dto.*;

/**
 *
 * @author student
 */
public interface PlaceOrderController extends SuperController<SuperDTO>{
    public boolean add(OrderDTO t,ArrayList<OrderDetailDTO> arList) throws Exception;

    
    public SuperDTO get(CustomerDTO t) throws Exception;

    /**
     *
     * @param t
     * @return
     * @throws Exception
     */
    
    public SuperDTO get(ItemDTO t) throws Exception;
    
    public ArrayList<CustomerDTO> getAllCustomer() throws RemoteException,SQLException;
    public ArrayList<ItemDTO> getAllItem() throws RemoteException,SQLException;
    
    //public ArrayList<Object> getAll(dtoType dto) throws Exception;
    
    
}
