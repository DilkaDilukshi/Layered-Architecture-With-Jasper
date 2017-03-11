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
import lk.ijse.thogakade.business.custom.CustomerBO;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.reservation.Reservation;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.CustomerService;
import lk.ijse.thogakade.reservation.impl.ReserVationImpl;

/**
 *
 * @author Dil
 */
public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService{
    private CustomerBO customerBO;
    
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    private static Reservation customerReservation = new ReserVationImpl();
   
    public CustomerServiceImpl()throws RemoteException{
        try {
            customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    @Override
    public boolean add(CustomerDTO customer) {
        try {
            boolean result = customerBO.add(customer);
            notifyAllObservers();
            return result;
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public CustomerDTO get(CustomerDTO dto) throws RemoteException {
        try {
            customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
            return customerBO.get(dto);
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws RemoteException {
        
//        ArrayList<CustomerDTO> all = null;
//        boolean add;
//        add = all.add(new CustomerDTO("C001","Lahiru","Hikkaduwa",12452));
//            
        try {
            customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        
            ArrayList<CustomerDTO> all=customerBO.getAll();
            
            return all;
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Come to service");
//        return all;
            return null;
    }

    @Override
    public boolean update(CustomerDTO dto) {
        try {
            boolean update = customerBO.update(dto);
            notifyAllObservers();
            return update;
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean delete(CustomerDTO dto) {
        try {
            boolean delete = customerBO.delete(dto);
            notifyAllObservers();
            return delete;
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
    public boolean reserve(String customerId, SuperService service) throws RemoteException {
        return customerReservation.reserve(customerId, this);
    }

    @Override
    public boolean release(String customerId) throws RemoteException {
        return customerReservation.release(customerId);
    }

    
    
}
