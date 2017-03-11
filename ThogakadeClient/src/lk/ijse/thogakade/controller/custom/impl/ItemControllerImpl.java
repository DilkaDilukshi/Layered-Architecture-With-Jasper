/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import lk.ijse.thogakade.controller.ServerConnectorFactory;
import lk.ijse.thogakade.controller.custom.ItemController;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.custom.ItemService;


public class ItemControllerImpl implements ItemController {
    private ItemService itemService;
    public ItemControllerImpl() throws Exception{
        itemService=(ItemService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
    }
    

    @Override
    public boolean add(ItemDTO dto) throws Exception {
        return itemService.add(dto);
    }

    @Override
    public boolean update(ItemDTO dto) throws Exception {
        return itemService.update(dto);
    }

    @Override
    public boolean delete(ItemDTO dto) throws Exception {
        return itemService.delete(dto);
    }

    @Override
    public ItemDTO get(ItemDTO dto) throws Exception {
        return itemService.get(dto);
    }

    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        return itemService.getAll();
    }

    @Override
    public void registorObserver(Observer observer) throws Exception {
        itemService.registerObserver(observer);
    }

    @Override
    public void unRegistorObserver(Observer observer) throws Exception {
        itemService.unregisterObserver(observer);
    }

    @Override
    public boolean reserve(String id) throws RemoteException {
        return itemService.reserve(id, itemService);
    }

    @Override
    public boolean release(String id) throws RemoteException {
        return itemService.release(id);
    }
    

    
    
}
