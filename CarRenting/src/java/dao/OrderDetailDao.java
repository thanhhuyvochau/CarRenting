/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import mapper.DetailMapper;
import model.DetailOrder;
import org.apache.log4j.Logger;
import ultis.DBUltils;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDao extends AbstractDao<DetailOrder> {
    final static Logger log = Logger.getLogger(OrderDetailDao.class.getName());

    public OrderDetailDao() {
    }

    public Vector<DetailOrder> searchByDateAndName(String name, Date checkIn, Date checkOut) {
        String sql = "SELECT d.id,d.id_order,d.car_id,d.quantity_renting,d.price,d.check_in,d.check_out,d.status,d.is_feedbacked\n"
                + "FROM order_detail d,cars c\n"
                + "WHERE c.car_name like ? AND c.id = d.car_id AND  ((d.check_in Between ? AND  ? OR  d.check_out Between ? AND  ?) OR  (d.check_in <=  ? AND d.check_out > ?))";
        Vector<DetailOrder> detailList = query(sql, new DetailMapper(), "%"+name+"%", checkIn, checkOut, checkIn, checkOut, checkIn, checkIn);
        return detailList;
    }

    public Vector<DetailOrder> searchByDateAndCate(int category, Date checkIn, Date checkOut) {
        String sql = "SELECT d.id,d.id_order,d.car_id,d.quantity_renting,d.price,d.check_in,d.check_out,d.status,d.is_feedbacked\n"
                + "FROM order_detail d,cars c\n"
                + "WHERE c.category = ? AND c.id = d.car_id AND  ((d.check_in Between ? AND  ? OR  d.check_out Between ? AND  ?) OR  (d.check_in <=  ? AND d.check_out > ?))";
        Vector<DetailOrder> detailList = query(sql, new DetailMapper(), category, checkIn, checkOut, checkIn, checkOut, checkIn, checkIn);
        return detailList;
    }

    public int paycheck(String email, BigDecimal total_price) {
        String sql = "INSERT INTO orders (email,total_price,order_date)\n"
                + "VALUES (?,?,?);";
        Connection cnn = DBUltils.getConnection();
        PreparedStatement pre = null;
        ResultSet result = null;
        Integer key = null;
        try {
            if (cnn != null) {
                pre = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pre.setString(1, email);
                pre.setBigDecimal(2,total_price);
                pre.setDate(3, new java.sql.Date(new Date().getTime()));
                pre.executeUpdate();
                result = pre.getGeneratedKeys();
                if (result.next()) {
                    key = result.getInt(1);
                }
            }
        } catch (Exception e) {
            //log
            log.info(e.toString());

        } finally {
            try {
                if (result != null) {
                    result.close();
                } else if (pre != null) {
                    pre.close();
                } else if (cnn != null) {
                    cnn.close();
                }
            } catch (Exception e) {
                log.info(e.toString());
            }

        }
        return key;
    }

    public boolean detail_save(Integer id_order, Integer car_id, Integer quantityRented, BigDecimal priceTotal, Date checkIn, Date checkOut) {
        try {
            String sql = "INSERT INTO order_detail (id_order,car_id,quantity_renting,price,check_in,check_out,status,is_feedbacked)\n"
                    + "VALUES (?,?,?,?,?,?,?,?);";
            updateQuery(sql, id_order, car_id, quantityRented, priceTotal, checkIn, checkOut, true, false);
        } catch (Exception e) {
            //log
            log.info(e.toString());
            return false;
        }
        return true;
    }
}
