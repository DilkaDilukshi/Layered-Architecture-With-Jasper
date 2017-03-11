/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business;

import java.util.ArrayList;
import lk.ijse.thogakade.dto.SuperDTO;

/**
 *
 * @author Dil
 * @param <T>
 */
public interface SuperBO<T extends SuperDTO> {

    /**
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public boolean add(T dto) throws Exception;

    public boolean update(T dto) throws Exception;

    public boolean delete(T dto) throws Exception;

    public T get(T dto) throws Exception;

    public ArrayList<T> getAll() throws Exception;
}
