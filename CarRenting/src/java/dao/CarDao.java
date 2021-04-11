/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Vector;
import mapper.CarMapper;
import model.Car;
import model.Page;

/**
 *
 * @author ADMIN
 */
public class CarDao extends AbstractDao<Car> {

    public CarDao() {
    }

    public Vector<Car> loadAllCar(Integer start, Integer end) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img \n"
                + "                FROM cars\n"
                + "                   WHERE is_avaible = 1\n"
                + "               ORDER BY car_name\n"
                + "                OFFSET ? ROWS \n"
                + "                FETCH NEXT ? ROWS ONLY";
        Vector<Car> carList = query(sql, new CarMapper(), start, end);
        return carList;
    }

    public Car getCarById(int carId) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img \n"
                + "                FROM cars\n"
                + "                   WHERE id = ? AND is_avaible = 1\n";
        Vector<Car> carList = query(sql, new CarMapper(),carId);
        if (!carList.isEmpty()) {
            return carList.get(0);
        } else {
            return null;
        }
    }

    public Page loadAllCarForPaging(Page page) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img \n"
                + "FROM cars \n";
        Vector<Car> carList = query(sql, new CarMapper());
        page.setTotalRecordNum(carList);
        return page;
    }

    public Vector<Car> searchByName(String name, int start, int end) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img\n"
                + "FROM cars c\n"
                + "WHERE c.car_name like ? AND is_avaible = 'true'"
                + "               ORDER BY c.car_name\n"
                + "                OFFSET ? ROWS \n"
                + "                FETCH NEXT ? ROWS ONLY";
        Vector<Car> carList = query(sql, new CarMapper(), "%"+name+"%", start, end);
        return carList;
    }

    public Page getPageForSN(String name, Page page) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img\n"
                + "FROM cars c\n"
                + "WHERE c.car_name like ? AND is_avaible = 'true'";
        Vector<Car> carList = query(sql, new CarMapper(), "%"+name+"%");
        page.setTotalRecordNum(carList);
        return page;
    }

    public Vector<Car> searchByCategory(int categoryId, int start, int end) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img\n"
                + "FROM cars c\n"
                + "WHERE c.category = ? AND is_avaible = 'true'"
                + "               ORDER BY c.car_name\n"
                + "                OFFSET ? ROWS \n"
                + "                FETCH NEXT ? ROWS ONLY";
        Vector<Car> carList = query(sql, new CarMapper(), categoryId, start, end);
        return carList;
    }

    public Page getPageForSC(int categoryId, Page page) {
        String sql = "SELECT id,car_name,color,year,category,price,quantity,img\n"
                + "FROM cars c\n"
                + "WHERE c.category = ? AND is_avaible = 'true'";
        Vector<Car> carList = query(sql, new CarMapper(), categoryId);
        page.setTotalRecordNum(carList);
        return page;
    }

   
}
