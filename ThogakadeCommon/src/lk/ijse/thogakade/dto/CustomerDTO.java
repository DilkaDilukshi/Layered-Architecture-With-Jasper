/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dto;

import java.math.BigDecimal;

/**
 *
 * @author Dil
 */
public class CustomerDTO extends SuperDTO{
    
    private String id;
    private String name;
    private String address;
    private Double salary;

    public CustomerDTO(String id) {
        this.id = id;
    }

    public CustomerDTO() {
    }
    
    

    public CustomerDTO(String id, String name, String address, Double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    
    
}
