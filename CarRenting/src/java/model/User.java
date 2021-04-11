/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class User {

    private String email;
    private String phone;
    private String name;
    private String address;
    private String status;
    private String password;
    private Date createDate;
    private String verifyCode;
    private String role;

    public User() {
    }

    public User(String email, String phone, String name, String address, String status, String password, Date createDate) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.status = status;
        this.password = password;
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String email, String phone, String name, String address, String status, String password, Date createDate, String verifyCode, String role) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.status = status;
        this.password = password;
        this.createDate = createDate;
        this.verifyCode = verifyCode;
        this.role = role;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
