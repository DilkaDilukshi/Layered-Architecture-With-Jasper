/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom.impl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.controller.ServerConnectorFactory;
import lk.ijse.thogakade.controller.custom.PlaceOrderController;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.dto.SuperDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.custom.*;
import lk.ijse.thogakade.views.PlaceOrderForm;

/**
 *
 * @author Dil
 */
public class PlaceOrderControllerImpl  implements PlaceOrderController{

    private final CustomerService customerService;
    private final OrderService orderService;
    private final ItemService itemService;
    
    public PlaceOrderControllerImpl() throws NotBoundException, MalformedURLException, RemoteException{
        customerService = (CustomerService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
        orderService=(OrderService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
        itemService=(ItemService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        
        //ServerConnectorFactory.getInstance().getRegisted(this);
                
    }
    

    /**
     *
     * @param t
     * @param arList
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(OrderDTO t,ArrayList<OrderDetailDTO> arList) throws Exception {
       return orderService.add(t,arList);
       
    }

    

    
    @Override
    public SuperDTO get(CustomerDTO t) throws Exception {
        return customerService.get(t);
    }

    @Override
    public SuperDTO get(ItemDTO t) throws Exception {
        return itemService.get(t);
    }

   

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws RemoteException,SQLException{
        return customerService.getAll();
    }
    @Override
    public ArrayList<ItemDTO> getAllItem() throws RemoteException,SQLException{
        return itemService.getAll();
    }
    public String getOrderId() throws RemoteException, SQLException{
        
        OrderDTO dto=new OrderDTO();
        dto = orderService.get(dto);
        if(dto.getId()==null){
            System.out.println("Order id is null");
        }
        return dto.getId();
    }

    @Override
    public boolean add(SuperDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(SuperDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(SuperDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SuperDTO get(SuperDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SuperDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registorObserver(Observer observer) throws Exception {
        customerService.registerObserver(observer);
        itemService.registerObserver(observer);
        
    }

    @Override
    public void unRegistorObserver(Observer observer) throws Exception {
        itemService.unregisterObserver(observer);
        customerService.unregisterObserver(observer);
    }

    @Override
    public boolean reserve(String id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean release(String id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    
}
