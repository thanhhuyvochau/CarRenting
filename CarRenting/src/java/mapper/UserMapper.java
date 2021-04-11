/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.sql.ResultSet;
import java.util.Date;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class UserMapper implements MapperInterface<User> {

    Logger log = Logger.getLogger(UserMapper.class.getName());

    public User mapper(ResultSet result) {
        User user = null;
        try {
            String email = result.getString("email");
            String phone = result.getString("phone");
            String name = result.getString("name");
            String address = result.getString("address");
            String status = result.getString("status");
            String password = "";
            Date createDate = result.getDate("create_date");
            String veryfi = result.getString("verify");
            String role = result.getString("role");
            user = new User(email, phone, name, address, status, password, createDate, veryfi, role);
        } catch (Exception ex) {
            //log
           
            log.info(ex.toString());
        }
        return user;

    }

}
