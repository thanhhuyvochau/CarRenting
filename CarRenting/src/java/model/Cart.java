/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import ultis.DateConvert;

/**
 *
 * @author ADMIN
 */
public class Cart {

    private Car car;
    private int quantity;
    private BigDecimal totalMoney;
    private Date checkIn;
    private Date checkOut;

    public Date getCheckIn() {
        return checkIn;
    }

    public String getCheckInString() {
        return DateConvert.convertDateToString(checkIn, "yyyy-MM-dd");
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getCheckOutString() {
        return DateConvert.convertDateToString(checkOut, "yyyy-MM-dd");
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Cart() {
    }

    public Cart(Car car, int quantity, BigDecimal totalMoney, Date checkIn, Date checkOut) {
        this.car = car;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPriceLocale() {
        return NumberFormat.getCurrencyInstance().format(getTotalMoney());
    }

    public BigDecimal getDayNum() {
        String dateBeforeString = DateConvert.convertDateToString(getCheckIn(), "yyyy-MM-dd");
        String dateAfterString = DateConvert.convertDateToString(getCheckOut(), "yyyy-MM-dd");
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        return new BigDecimal(noOfDaysBetween);
    }

    public String getTotalOfPrice() {
        BigDecimal days = getDayNum();
        BigDecimal quantity = new BigDecimal(getQuantity());
        BigDecimal temp = days.multiply(quantity);
        BigDecimal price = temp.multiply(car.getPrice());
        setTotalMoney(price);
        return getPriceLocale();
    }

}
