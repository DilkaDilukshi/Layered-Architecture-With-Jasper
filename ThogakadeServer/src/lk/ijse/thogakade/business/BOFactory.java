/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business;

import lk.ijse.thogakade.business.custom.impl.CustomerBOImpl;
import lk.ijse.thogakade.business.custom.impl.ItemBOImpl;
import lk.ijse.thogakade.business.custom.impl.OrderBOImpl;


/**
 *
 * @author Dil
 */
public class BOFactory {
    
    public enum BOTypes{
        CUSTOMER, ORDER,ITEM;
    }
    
    private static BOFactory boFactory;
    
    private BOFactory(){
        
    }
    
    public static BOFactory getInstance(){
        if (boFactory ==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    
    public SuperBO getBO(BOTypes boType) throws Exception{
        switch(boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ITEM:
                return new ItemBOImpl();
            default:
                return null;
        }
    }
}
