/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import error.CarState;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Car;
import model.Category;
import model.Page;
import org.apache.log4j.Logger;
import service.CarService;
import service.CategoryService;
import ultis.DateConvert;

/**
 *
 * @author ADMIN
 */
public class SearchServlet extends HttpServlet {
     final static Logger log = Logger.getLogger(SearchServlet.class.getName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/view/search.jsp";
        try{     
        String action = request.getParameter("action");
        
        CarService carService = new CarService();
        CategoryService cateService = new CategoryService();
        CarState carState = new CarState();
        String pattern = "yyyy-MM-dd";
        Vector<Category> cateList = cateService.getAllCate();
        request.setAttribute("categoryList", cateList);
        //none-action check
        if (action == null) {
            carState.setMessage(CarState.NONE_ACTION);
        } else if (action.equalsIgnoreCase("car_name") || action.equalsIgnoreCase("car_category")) {
            String checkIn = request.getParameter("txtDateFrom");
            String checkOut = request.getParameter("txtDateTo");
           
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Date checkInC = DateConvert.convertStringToDate(checkIn, pattern);
            Date checkOutC = DateConvert.convertStringToDate(checkOut, pattern);
            String name = request.getParameter("txtCarName");
            String categoryId = request.getParameter("category");
            //date check
            System.out.println("DATE CHECK:"+checkInC.compareTo(new Date()));
            if (checkInC.compareTo(DateConvert.convertStringToDate(DateConvert.convertDateToString(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")) < 0) {
                carState.setMessage(CarState.DATE_PRESENT_ERROR);
                request.setAttribute("message", carState.getMessage());
                request.setAttribute("nameSearched", name);
                request.setAttribute("quantity", quantity);
                String checkOutNew = DateConvert.convertDateToString(new Date(), pattern);
                String checkInNew = DateConvert.convertDateToString(new Date(), pattern);
                request.setAttribute("checkIn", checkOutNew);
                request.setAttribute("checkOut", checkInNew);

            } else if (checkInC.compareTo(checkOutC) >= 0) {
                carState.setMessage(CarState.DATE_OUT_IN_INVALID);
                request.setAttribute("message", carState.getMessage());
                request.setAttribute("nameSearched", name);
                request.setAttribute("quantity", quantity);
                String checkOutNew = DateConvert.convertDateToString(new Date(), pattern);
                String checkInNew = DateConvert.convertDateToString(new Date(), pattern);
                request.setAttribute("checkIn", checkOutNew);
                request.setAttribute("checkOut", checkInNew);
            } else {
                //-----------------
                Page page = new Page();
                String pageS = request.getParameter("page");
                if (pageS != null) {
                    if (pageS.trim().length() > 0) {
                        page.setIndexPage(Integer.parseInt(pageS));
                    }
                }
                //go to searching part for identified searching option
                if (action.equalsIgnoreCase("car_name")) {

                    Vector<Car> listCar = null;
                    //check field of searching option
                    if (name != null) {
                        if (name.trim().length() > 0) {
                            listCar = carService.searchByCarName(name, checkInC, checkOutC, quantity, page);
                            request.setAttribute("listCars", listCar);
                            request.setAttribute("nameSearched", name);
                            request.setAttribute("searchField", "txtCarName");
                            request.setAttribute("dataSearch", name);
                        } else {
                            carState.setMessage(CarState.FIELD_NULL);
                            request.setAttribute("message", carState.getMessage());
                        }

                    } else {
                        carState.setMessage(CarState.FIELD_NULL);
                        request.setAttribute("message", carState.getMessage());
                    }
                    //-------------------

                } else if (action.equalsIgnoreCase("car_category")) {
                    ///code here
                    Vector<Car> listCar = null;
                    if (categoryId != null) {
                        if (categoryId.trim().length() > 0) {
                            listCar = carService.searchByCarCategory(Integer.parseInt(categoryId), checkInC, checkOutC, quantity, page);
                            request.setAttribute("listCars", listCar);
                            request.setAttribute("categorySearched", categoryId);
                            request.setAttribute("searchField", "category");
                            request.setAttribute("dataSearch", categoryId);
                        } else {
                            carState.setMessage(CarState.FIELD_NULL);
                            request.setAttribute("message", carState.getMessage());
                        }

                    } else {
                        carState.setMessage(CarState.FIELD_NULL);
                        request.setAttribute("message", carState.getMessage());
                    }
                }
                request.setAttribute("checkIn", checkIn);
                request.setAttribute("checkOut", checkOut);
                request.setAttribute("quantity", quantity);
                request.setAttribute("page", page);
                request.setAttribute("action", action);

            }
        } else {
            String checkOutNew = DateConvert.convertDateToString(new Date(), pattern);
            String dateTomorow = DateConvert.addOneDay(checkOutNew);
            String checkInNew = DateConvert.convertDateToString(new Date(), pattern);
            request.setAttribute("checkIn", checkOutNew);
            request.setAttribute("checkOut", dateTomorow);

        }
        }catch(Exception e){
            //log
            log.info(e.toString());
            url = "/view.error.jsp";
        }
        request.getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
