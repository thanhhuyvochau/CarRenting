/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CarDao;
import dao.OrderDetailDao;
import java.util.Date;
import java.util.Vector;
import model.Car;
import model.DetailOrder;
import model.Page;

/**
 *
 * @author ADMIN
 */
public class CarService {

    private CarDao carDao;
    private OrderDetailDao detailDao;

    public CarService() {
        carDao = new CarDao();
        detailDao = new OrderDetailDao();
    }

    public Vector<Car> getAllCars(Page page) {

        page = carDao.loadAllCarForPaging(page);
        int start = (page.getIndexPage() * page.getNumObjectInPage()) - page.getNumObjectInPage();
        int end = page.getNumObjectInPage();
        Vector<Car> carList = carDao.loadAllCar(start, end);
        return carList;
    }
    public Car getCarById(int id){
        Car car = carDao.getCarById(id);
        CategoryService cateService = new CategoryService();
        String categoryName = cateService.getCategoryName(car.getCategory());
        car.setCategoryName(categoryName);
        return car;
    }
    public Vector<Car> searchByCarName(String name, Date checkIn, Date checkOut, int quantity, Page page) {

        Vector<Car> listAllSearch = null;
        Vector<DetailOrder> listOfRented = null;
        Vector<Car> finalList = null;
        int start = (page.getIndexPage() * page.getNumObjectInPage()) - page.getNumObjectInPage();
        int end = page.getNumObjectInPage();
        listAllSearch = carDao.searchByName(name, start, end);
        listOfRented = detailDao.searchByDateAndName(name, checkIn, checkOut);
        page = carDao.getPageForSN(name, page);

        for (Car car : listAllSearch) {
            for (DetailOrder detail : listOfRented) {
                if (car.getId() == detail.getCarId()) {
                    car.setQuantity(car.getQuantity() - detail.getQuantityRented());
                }
            }
        }
        finalList = new Vector<Car>();
        for (Car car : listAllSearch) {
            if (car.getQuantity() >= quantity) {
                finalList.add(car);
            }
        }
        
        CategoryService cateService = new CategoryService();
        for(Car car:finalList){
            String cateName = cateService.getCategoryName(car.getCategory());
            car.setCategoryName(cateName);
        }
      
        return finalList;
    }
    public Vector<Car> searchByCarCategory(int categoryId, Date checkIn, Date checkOut, int quantity, Page page) {

        Vector<Car> listAllSearch = null;
        Vector<DetailOrder> listOfRented = null;
        Vector<Car> finalList = null;
        int start = (page.getIndexPage() * page.getNumObjectInPage()) - page.getNumObjectInPage();
        int end = page.getNumObjectInPage();
        listAllSearch = carDao.searchByCategory(categoryId, start, end);
        listOfRented = detailDao.searchByDateAndCate(categoryId, checkIn, checkOut);
        page = carDao.getPageForSC(categoryId, page);

        for (Car car : listAllSearch) {
            for (DetailOrder detail : listOfRented) {
                if (car.getId() == detail.getCarId()) {
                    car.setQuantity(car.getQuantity() - detail.getQuantityRented());
                }
            }
        }
        finalList = new Vector<Car>();
        for (Car car : listAllSearch) {
            if (car.getQuantity() >= quantity) {
                finalList.add(car);
            }
        }
        
        CategoryService cateService = new CategoryService();
        for(Car car:finalList){
            String cateName = cateService.getCategoryName(car.getCategory());
            car.setCategoryName(cateName);
        }
       
        return finalList;
    }
   

}
