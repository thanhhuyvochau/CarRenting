/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;
import model.DetailOrder;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class DetailMapper implements MapperInterface<DetailOrder> {
    final static Logger log = Logger.getLogger(DetailMapper.class.getName());

    public DetailMapper() {
    }

    public DetailOrder mapper(ResultSet result) {
        DetailOrder detail = null;
        try {
            int id = result.getInt("id");
            int id_order = result.getInt("id_order");
            int car_id = result.getInt("car_id");
            int quantity = result.getInt("quantity_renting");
            BigDecimal price = result.getBigDecimal("price");
            Date checkIn = result.getDate("check_in");
            Date CheckOut = result.getDate("check_out");
            Boolean status = result.getBoolean("status");
            Boolean isFeedback = result.getBoolean("is_feedbacked");
            detail = new DetailOrder(id, id_order, car_id, quantity, price, checkIn, CheckOut, status, isFeedback);
        } catch (Exception e) {
            //log
            log.info(e.toString());
        }
        return detail;
    }

}
