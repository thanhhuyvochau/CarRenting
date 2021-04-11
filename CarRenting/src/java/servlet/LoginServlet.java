/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import error.UserState;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import ultis.VerifyRecaptcha;
import validation.UserValidator;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {
   
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserService service = new UserService();
        User user = null;

        UserValidator valider = new UserValidator();
        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        String url = "/view/login.jsp";
        String email = request.getParameter("email");
        if (verify == false) {
            url ="/view/login.jsp";
             request.setAttribute("email", email);
            request.setAttribute("message","Missed recapcha");
        } else {
            user = (User) session.getAttribute("identify");
            if (user == null) {               
                String password = request.getParameter("password");
                if (email == null || password == null) {
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else {
                    UserState state = valider.checkUserForLogin(email, password);
                    if (state.getMessage().equals(UserState.VALID_SUCCESS)) {
                        user = service.login(email, password);
                        if (user != null) {
                            session.setAttribute("identify", user);
                            System.out.println("status user:" + user.getStatus());
                            if (user.getStatus().trim().equalsIgnoreCase("new")) {
                                url = "/view/verify.jsp";
                            } else {
                                url = "/Servlet/HomePageServlet"; //just check login
                            }
                        } else {
                            request.setAttribute("message","Email or Password Is Wrong, Try Again!");
                            request.setAttribute("email", email);
                        }

                    }
                }

            } else {
                url = "/Servlet/HomepageServlet";
            }
        }

      
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
