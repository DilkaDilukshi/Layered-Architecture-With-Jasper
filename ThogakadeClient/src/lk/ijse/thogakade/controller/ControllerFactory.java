/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import lk.ijse.thogakade.controller.custom.impl.CustomerControllerImpl;
import lk.ijse.thogakade.controller.custom.impl.ItemControllerImpl;
import lk.ijse.thogakade.controller.custom.impl.PlaceOrderControllerImpl;

/**
 *
 * @author Dil
 */
public class ControllerFactory {
    
    public enum ControllerTypes{
        CUSTOMER,PLACE_ORDER,ITEM;
    }
    
    private static ControllerFactory controllerFactory;
    private JFrame frame;
    
    private ControllerFactory(){
        
    }
    
    public static ControllerFactory getInstance(){
        if (controllerFactory == null){
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }
    public void getRegisterd(JFrame frame){
        this.frame=frame;
    }
    public SuperController getController(ControllerTypes controllerType) throws NotBoundException, MalformedURLException, RemoteException, Exception{
        switch(controllerType){
            case CUSTOMER:
                return new CustomerControllerImpl();
            case PLACE_ORDER:        
                return new PlaceOrderControllerImpl();           
            case ITEM:
                return new ItemControllerImpl();
                
            default:
                return null;
        }
    }
    
}
