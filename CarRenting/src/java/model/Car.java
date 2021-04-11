/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author ADMIN
 */
public class Car {
    private int id;
    private String carName;
    private String color;
    private String year;
    private int category;
    private BigDecimal price;
    private int quantity;
    private String imgPath;
     private String categoryName;
    public Car() {
    }

    public Car(int id, String carName, String color, String year, int category, BigDecimal price, int quantity, String imgPath) {
        this.id = id;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getPriceLocale(){
        return NumberFormat.getCurrencyInstance().format(getPrice());
    }
    public void setCategoryName(String cateName){
        this.categoryName = cateName;
    }
   public String getCategoryName(){
       return this.categoryName;
   }
    
}
