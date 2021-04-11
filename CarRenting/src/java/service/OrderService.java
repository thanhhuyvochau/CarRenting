/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.OrderDetailDao;
import error.CarState;
import java.math.BigDecimal;
import java.util.Vector;
import model.Car;
import model.Cart;
import model.DetailOrder;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class OrderService {

    final static Logger log = Logger.getLogger(OrderService.class.getName());
    private static OrderDetailDao orderDetaileDao;

    private static OrderDetailDao getDaoInstance() {
        if (OrderService.orderDetaileDao == null) {
            orderDetaileDao = new OrderDetailDao();
        }
        return orderDetaileDao;
    }
    private OrderDetailDao dao;

    public OrderService() {
        dao = getDaoInstance();

    }

    public CarState paycheck(Vector<Cart> cartList, User user) {
        CarState carState = new CarState();
        try {
            for (Cart cart : cartList) {
                Car car = cart.getCar();
                int quantity = cart.getQuantity();
                int quantityCheck = car.getQuantity();
                Vector<DetailOrder> details = dao.searchByDateAndName(car.getCarName(), cart.getCheckIn(), cart.getCheckOut());
                int temp = car.getQuantity();
                for (DetailOrder d : details) {
                    if (d.getCarId() == car.getId()) {
                        quantityCheck = temp - d.getQuantityRented();
                        temp = quantityCheck;
                    }
                }
                if (quantityCheck < quantity) {
                    carState.setMessage(CarState.RENT_FAIL);
                    return carState;
                }

            }
            BigDecimal totalPrice = getTotalOfCarts(cartList);
            Integer key = dao.paycheck(user.getEmail().trim(), totalPrice);
            boolean detailWrite = writeDetail(key, cartList);
            if (detailWrite) {
                carState.setMessage(CarState.RENT_SUCCESS);
            }
        } catch (Exception e) {
            //log
            log.info(e.toString());
            carState.setMessage(CarState.RENT_FAIL);
        }
        return carState;
    }

    public boolean writeDetail(Integer key, Vector<Cart> cartList) {
        try {
            for (Cart cart : cartList) {
                Car car = cart.getCar();
                dao.detail_save(key, car.getId(), cart.getQuantity(), cart.getTotalMoney(), cart.getCheckIn(), cart.getCheckOut());
            }

        } catch (Exception e) {
            //log
            log.info(e.toString());
            return false;
        }
        return true;
    }

    public static BigDecimal getTotalOfCarts(Vector<Cart> cartList) {
        BigDecimal total = new BigDecimal(0);
        try {
            for (int i = 0; i < cartList.size(); i++) {
                Cart tempCart = cartList.get(i);
                tempCart.getTotalOfPrice();
                total = total.add(tempCart.getTotalMoney());
            }
        } catch (Exception e) {
            //log
            log.info(e.toString());
        }
        return total;
    }
}
