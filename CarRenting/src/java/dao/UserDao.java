/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Vector;
import mapper.UserMapper;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserDao extends AbstractDao<User> {

    public User authenticateUser(String email, String password) {
        String sql = "SELECT email,phone,name,address,create_date,status,verify,role\n"
                + "FROM users \n"
                + "WHERE email like ? AND password like ?";
        Vector<User> result = new Vector<User>();
        result = query(sql, new UserMapper(), email, password);
        User user = null;
        if (!result.isEmpty()) {
            user = result.get(0);
        }
        return user;
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (email,phone,name,address,create_date,status,password,verify,role)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?);";
        String email = user.getEmail();
        String phone = user.getPhone();
        String userName = user.getName();
        String address = user.getAddress();
        java.sql.Date createDate = new java.sql.Date(user.getCreateDate().getTime());
        String status = user.getStatus();
        String password = user.getPassword();
        String verify = user.getVerifyCode();
        String role = user.getRole();
        boolean registeResult = updateQuery(sql, email, phone, userName, address, createDate, status, password, verify, role);
        return registeResult;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT email,phone,name,address,create_date,status,verify,role\n"
                + "FROM users\n"
                + "WHERE email like ?";
        Vector<User> result = new Vector<User>();
        result = query(sql, new UserMapper(), email);
        User user = null;
        if (!result.isEmpty()) {
            user = result.get(0);
        }
        return user;
    }

    public User getUserForVerify(String email, String verifyCode) {
        String sql = "SELECT email,phone,name,address,create_date,status,verify,role\n"
                + "FROM users\n"
                + "WHERE email like ? AND verify = ?";
        Vector<User> result = new Vector<User>();
        result = query(sql, new UserMapper(), email,verifyCode);
        System.out.println(result.size());
        User user = null;
        if (!result.isEmpty()) {
            user = result.get(0);
        }
        return user;
    }

    public boolean verifyUser(String email, String verifyCode) {
        String sql = "UPDATE users\n"
                + "SET status = 'active'\n"
                + "WHERE email like ? AND verify = ?;";
        boolean result = updateQuery(sql, email, verifyCode);
        return result;
    }

   
}
