/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dto;

/**
 *
 * @author Dil
 */
public class ItemDTO extends SuperDTO{
    private String code;
    private String Description;
    private Double unitPrice;
    private Integer qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String code, String Description, Double unitPrice, Integer qtyOnHand) {
        this.code = code;
        this.Description = Description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

   

    public ItemDTO(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the unitPrice
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the qtyOnHand
     */
    public Integer getQtyOnHand() {
        return qtyOnHand;
    }

    /**
     * @param qtyOnHand the qtyOnHand to set
     */
    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    
    
}
