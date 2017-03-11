/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao;

import java.sql.SQLException;
import lk.ijse.thogakade.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.thogakade.dao.custom.impl.ItemDAOImpl;
import lk.ijse.thogakade.dao.custom.impl.OrderDAOImpl;
import lk.ijse.thogakade.dao.custom.impl.OrderDetailDAOImpl;

/**
 *
 * @author Dil
 */
public class DAOFactory {

    public enum DAOTypes {

        CUSTOMER, ORDER,ITEM,ORDER_DETAIL;
    }

    private static DAOFactory dAOFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (dAOFactory == null) {
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }

    public SuperDAO getDAO(DAOTypes daoType) throws SQLException {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
    
}
