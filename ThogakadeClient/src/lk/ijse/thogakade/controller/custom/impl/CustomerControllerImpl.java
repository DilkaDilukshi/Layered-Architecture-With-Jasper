/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom.impl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lk.ijse.thogakade.controller.ServerConnectorFactory;
import lk.ijse.thogakade.controller.custom.CustomerController;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.CustomerService;

/**
 *
 * @author Dil
 */
public class CustomerControllerImpl implements CustomerController {


    private final CustomerService customerService;

    public CustomerControllerImpl() throws NotBoundException, MalformedURLException, RemoteException {

        customerService = (CustomerService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);

    }

    @Override
    public boolean add(CustomerDTO t) throws Exception {
        customerService.add(t);
        return true;
    }

    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        return customerService.update(dto);
    }

    @Override
    public boolean delete(CustomerDTO dto) throws Exception {
        return customerService.delete(dto);
    }

    @Override
    public CustomerDTO get(CustomerDTO dto) throws Exception {
        return customerService.get(dto);
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        return customerService.getAll();
    }

    @Override
    public void registorObserver(Observer observer) throws Exception {
        customerService.registerObserver(observer);
    }

    @Override
    public void unRegistorObserver(Observer observer) throws Exception {
        customerService.unregisterObserver(observer);
    }

    @Override
    public boolean reserve(String id) throws RemoteException {
        return customerService.reserve(id, customerService);
    }

    @Override
    public boolean release(String id) throws RemoteException {
        return customerService.release(id);
    }

  

    

    
}
