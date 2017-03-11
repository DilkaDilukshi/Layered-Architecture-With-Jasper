/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.reservation.impl;

import java.util.HashMap;
import lk.ijse.thogakade.reservation.Reservation;
import lk.ijse.thogakade.service.SuperService;

/**
 *
 * @author Dil
 */
public class ReserVationImpl implements Reservation{
    private static final HashMap<String, SuperService>reserveList=new HashMap<>();
    
    @Override
    public boolean reserve(String id,SuperService service){
        if(reserveList.containsKey(id)){
            if(reserveList.get(id)==service){
                return true;
            }else{
                return false;
            }
            
        }else{
            reserveList.put(id, service);
            return true;
        }
    }
    
    @Override
    public boolean release(String id){
        if(reserveList.containsKey(id)){
            reserveList.remove(id);
            
            return true;
        }else{
            return false;
        }
            
    }
    
}
