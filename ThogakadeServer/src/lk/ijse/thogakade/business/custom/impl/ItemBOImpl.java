/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.thogakade.business.custom.ItemBO;
import lk.ijse.thogakade.dao.ConnectionFactory;
import lk.ijse.thogakade.dao.DAOFactory;
import lk.ijse.thogakade.dao.SuperDAO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.dto.SuperDTO;

/**
 *
 * @author Dil
 */
public class ItemBOImpl implements ItemBO {

    private SuperDAO dao;
    private Connection connection;

    public ItemBOImpl() {
        try {
            dao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

        } catch (SQLException ex) {
            Logger.getLogger(ItemBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ItemDTO get(ItemDTO dto) throws SQLException, Exception {

        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        ItemDTO get = (ItemDTO) dao.get(dto);
        connection.close();
        return get;

    }

    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);        
        ArrayList all = dao.getAll();
        connection.close();
        return all;

    }

    @Override
    public boolean add(ItemDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        boolean add = dao.add(dto);
        connection.close();
        return add;
    }

    @Override
    public boolean update(ItemDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        boolean update = dao.update(dto);
        connection.close();
        return update;
    }

    @Override
    public boolean delete(ItemDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        boolean delete = dao.delete(dto);
        connection.close();
        return delete;
    }

}
