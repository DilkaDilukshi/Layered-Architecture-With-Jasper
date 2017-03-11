/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.thogakade.business.BOFactory;
import lk.ijse.thogakade.business.custom.OrderBO;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.OrderService;

/**
 *
 * @author Dil
 */
public class OrderServiceImpl extends UnicastRemoteObject implements OrderService{
    private OrderBO bo;
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    
    
    public OrderServiceImpl() throws RemoteException{
        try {
            bo = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param dto
     * @param arList
     * @return
     */
    @Override
    public boolean add(OrderDTO dto,ArrayList<OrderDetailDTO> arList) {
        try {
            return bo.add(dto, arList);
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public OrderDTO get(OrderDTO dto) {
        try {
            return bo.get(dto);
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }

    @Override
    public ArrayList<OrderDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(OrderDTO dto){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public boolean update(OrderDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(OrderDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void registerObserver(Observer observer) throws RemoteException {
        alObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws RemoteException {
        alObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers() throws RemoteException {
        for (Observer alObserver : alObservers) {
            alObserver.update();
        }
    }

    @Override
    public boolean reserve(String id, SuperService service) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean release(String id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

    

    
}
