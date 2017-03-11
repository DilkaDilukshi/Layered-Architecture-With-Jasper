/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lk.ijse.thogakade.dto.SuperDTO;
import lk.ijse.thogakade.observers.Subject;
import lk.ijse.thogakade.reservation.Reservation;

/**
 *
 * @author Dil
 * @param <T>
 */
public interface SuperService<T extends SuperDTO> extends Remote, Subject,Reservation{

    public boolean add(T dto) throws RemoteException;

    public boolean update(T dto) throws RemoteException;

    public Boolean delete(T dto) throws RemoteException;

    public T get(T dto) throws RemoteException;

    public ArrayList<T> getAll() throws RemoteException;

}
