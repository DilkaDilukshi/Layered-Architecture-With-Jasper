/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.impl.CustomerServiceImpl;
import lk.ijse.thogakade.service.custom.impl.ItemServiceImpl;
import lk.ijse.thogakade.service.custom.impl.OrderServiceImpl;

/**
 *
 * @author stDiludent
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
    private static ServiceFactory serviceFactory;
    
    private ServiceFactoryImpl()throws RemoteException{
        
        
    }
    
    public static ServiceFactory getInstance()throws RemoteException{
        if (serviceFactory == null){
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    /**
     *
     * @param serviceType
     * @return
     * @throws RemoteException
     */
    @Override
    public SuperService getService(ServiceTypes serviceType) throws RemoteException{
        switch(serviceType){
            case CUSTOMER:
                return new CustomerServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case ITEM:
                return new ItemServiceImpl();
            default:
                return null;
        }
    }

    
}
