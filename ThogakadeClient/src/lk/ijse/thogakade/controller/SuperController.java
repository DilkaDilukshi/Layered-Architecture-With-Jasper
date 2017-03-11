/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import lk.ijse.thogakade.dto.SuperDTO;
import lk.ijse.thogakade.observers.Observer;

/**
 *
 * @author Dil
 * @param <T>
 */
public interface SuperController<T extends SuperDTO>{

    public boolean add(T dto) throws Exception;

    public boolean update(T dto) throws Exception;

    public boolean delete(T dto) throws Exception;

    public T get(T dto) throws Exception;

    public ArrayList<T> getAll() throws Exception;
    

    public void registorObserver(Observer observer) throws Exception;

    public void unRegistorObserver(Observer observer) throws Exception;
    

    public boolean reserve(String id) throws RemoteException;

    public boolean release(String id) throws RemoteException;

}
