/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import error.CarState;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Car;
import model.Cart;
import service.CarService;
import service.OrderService;
import static servlet.SearchServlet.log;
import ultis.DateConvert;

/**
 *
 * @author ADMIN
 */
public class CartServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        String url = request.getParameter("backURL");
        String carId = request.getParameter("carId");
        String checkIn = request.getParameter("txtDateFroms");
        CarService carServie = new CarService();
        String checkOut = request.getParameter("txtDateTos");
        HttpSession session = request.getSession();
        String id = request.getParameter("carId");
        Vector<Cart> cartList = (Vector<Cart>) session.getAttribute("cartList");
        if (cartList == null) {
            cartList = new Vector<Cart>();
        }
        if (action == null) {
            url = "/view/error.jsp";
        } else if (action.equalsIgnoreCase("redirect")) {
            url = "/view/cart.jsp";
        } else if (action.equalsIgnoreCase("add")) {
            if (checkIn == null || checkOut == null) {
                checkIn = DateConvert.convertDateToString(new Date(), "yyyy-MM-dd");
                checkOut = DateConvert.addOneDay(checkIn);
            }
            String quantity = request.getParameter("quantity");
            if (quantity == null) {
                quantity = "1";
            }
            int quanInt = Integer.parseInt(quantity);
            //--Check exist in carts
            int idInt = Integer.parseInt(id);
            boolean duplicate = false;
            for (Cart cart : cartList) {
                Date dateIn = DateConvert.convertStringToDate(checkIn, "yyyy-MM-dd");
                Date dateOut = DateConvert.convertStringToDate(checkOut, "yyyy-MM-dd");
                if (cart.getCar().getId() == idInt && dateIn.equals(DateConvert.convertStringToDate(cart.getCheckInString(), "yyyy-MM-dd"))
                        && dateOut.equals(DateConvert.convertStringToDate(cart.getCheckOutString(), "yyyy-MM-dd"))) {
                    cart.setQuantity(cart.getQuantity() + quanInt);
                    cart.setCheckIn(dateIn);
                    cart.setCheckOut(dateOut);
                    cart.setQuantity(quanInt + cart.getQuantity());
                    duplicate = true;
                }
            }
            if (!duplicate) {
                Cart cart = new Cart();
                Car car = carServie.getCarById(idInt);
                cart.setCar(car);
                Date dateIn = DateConvert.convertStringToDate(checkIn, "yyyy-MM-dd");
                Date dateOut = DateConvert.convertStringToDate(checkOut, "yyyy-MM-dd");
                cart.setCheckIn(dateIn);
                cart.setCheckOut(dateOut);
                cart.setQuantity(quanInt);
                cartList.add(cart);
                request.setAttribute("txtDateFrom", checkIn);
                request.setAttribute("txtDateTo", checkOut);
            }
            session.setAttribute("cartList", cartList);
        } else if (action.equalsIgnoreCase("increase")) {
            int carIdInt = Integer.parseInt(carId);
            int quanInt = 1;
            for (Cart cart : cartList) {
                if (cart.getCar().getId() == carIdInt) {
                    cart.setQuantity(cart.getQuantity() + quanInt);
                    Date dateIn = DateConvert.convertStringToDate(checkIn, "yyyy-MM-dd");
                    Date dateOut = DateConvert.convertStringToDate(checkOut, "yyyy-MM-dd");
                    cart.setCheckIn(dateIn);
                    cart.setCheckOut(dateOut);
                    if (cart.getQuantity() >= cart.getCar().getQuantity()) {
                        cart.setQuantity(cart.getCar().getQuantity());
                    }
                }
            }

            url = "/view/cart.jsp";
            session.setAttribute("cartList", cartList);
        } else if (action.equalsIgnoreCase("descrease")) {
            int carIdInt = Integer.parseInt(carId);
            int quanInt = 1;
            for (Cart cart : cartList) {
                if (cart.getCar().getId() == carIdInt) {
                    cart.setQuantity(cart.getQuantity() - quanInt);
                    Date dateIn = DateConvert.convertStringToDate(checkIn, "yyyy-MM-dd");
                    Date dateOut = DateConvert.convertStringToDate(checkOut, "yyyy-MM-dd");
                    cart.setCheckIn(dateIn);
                    cart.setCheckOut(dateOut);
                    if (cart.getQuantity() <= 1) {
                        cart.setQuantity(1);
                    }
                }
            }
            session.setAttribute("cartList", cartList);
            url = "/view/cart.jsp";
        } else if (action.equalsIgnoreCase("remove")) {
            int carIdInt = Integer.parseInt(carId);
            for (int i = 0; i < cartList.size(); i++) {
                Cart tempCart = cartList.get(i);
                Car tempCar = tempCart.getCar();
                if (tempCar.getId() == carIdInt) {
                    cartList.remove(i);
                }
            }
            session.setAttribute("cartList", cartList);
            url = "/view/cart.jsp";
        } else if (action.equalsIgnoreCase("update")) {
            CarState state = new CarState();
            int carIdInt = Integer.parseInt(carId);
            for (int i = 0; i < cartList.size(); i++) {
                Cart tempCart = cartList.get(i);
                Car tempCar = tempCart.getCar();
                if (tempCar.getId() == carIdInt) {
                    Date dateIn = DateConvert.convertStringToDate(checkIn, "yyyy-MM-dd");
                    Date dateOut = DateConvert.convertStringToDate(checkOut, "yyyy-MM-dd");
                    if (dateIn.compareTo(dateOut) >= 0) {
                        state.setMessage(CarState.DATE_OUT_IN_INVALID);
                        request.setAttribute("message", state.getMessage());
                    } else if (dateIn.compareTo(DateConvert.convertStringToDate(DateConvert.convertDateToString(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")) < 0) {
                        state.setMessage(CarState.DATE_PRESENT_ERROR);
                        request.setAttribute("message", state.getMessage());
                    } else {
                        tempCart.setCheckIn(dateIn);
                        tempCart.setCheckOut(dateOut);
                    }
                    break;
                }
            }
            session.setAttribute("cartList", cartList);
            url = "/view/cart.jsp";
        }
        request.setAttribute("totalAll", NumberFormat.getCurrencyInstance().format(OrderService.getTotalOfCarts(cartList)));
        request.getRequestDispatcher(url).forward(request, response);
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
