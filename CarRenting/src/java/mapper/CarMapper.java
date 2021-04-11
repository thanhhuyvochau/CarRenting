/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import model.Car;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class CarMapper implements MapperInterface<Car> {
    final static Logger log = Logger.getLogger(CarMapper.class.getName());

    public Car mapper(ResultSet result) {
        Car car = null;
        try {
            int id = result.getInt("id");
            String carName = result.getString("car_name");
            String color = result.getString("color");
            String year = result.getString("year");
            int category = result.getInt("category");
            BigDecimal price = result.getBigDecimal("price");
            int quantity = result.getInt("quantity");
            String imgPath = result.getString("img");
            car = new Car(id, carName, color, year, category, price, quantity, imgPath);
        } catch (Exception e) {
            //loog
            log.info(e.toString());
        }
        return car;
    }

}
