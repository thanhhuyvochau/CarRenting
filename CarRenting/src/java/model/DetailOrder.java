/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DetailOrder {

    private int id;
    private int idOrder;
    private int carId;
    private int quantityRented;
    private BigDecimal price;
    private Date checkIn;
    private Date checkOut;
    private Boolean status;
    private Boolean isFeedbacked;

    public DetailOrder() {
        isFeedbacked = false;
        quantityRented = 0;
    }

    public DetailOrder(int id, int idOrder, int carId, int quantityRented, BigDecimal price, Date checkIn, Date checkOut, Boolean status, Boolean isFeedbacked) {
        this.id = id;
        this.idOrder = idOrder;
        this.carId = carId;
        this.quantityRented = quantityRented;
        this.price = price;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.isFeedbacked = isFeedbacked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getQuantityRented() {
        return quantityRented;
    }

    public void setQuantityRented(int quantityRented) {
        this.quantityRented = quantityRented;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsFeedbacked() {
        return isFeedbacked;
    }

    public void setIsFeedbacked(Boolean isFeedbacked) {
        this.isFeedbacked = isFeedbacked;
    }

}
