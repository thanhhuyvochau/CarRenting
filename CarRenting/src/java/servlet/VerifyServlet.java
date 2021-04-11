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
import service.UserService;

/**
 *
 * @author ADMIN
 */
public class VerifyServlet extends HttpServlet {

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
        User user = (User) session.getAttribute("identify");
        UserService service = new UserService();
        String url = "/view/verify.jsp";
        String email = user.getEmail().trim();
        String action = request.getParameter("action");
        if (action != null) {
            if (action.trim().equalsIgnoreCase("resend")) {
                UserState state = service.resendMail(email);
                System.out.println("message:"+state.getMessage());
                request.setAttribute("message", state.getMessage());
            }
        } else {
            String verifyCode = request.getParameter("verify-code");
            if (verifyCode != null) {
                verifyCode = verifyCode.trim();
            }
            if (user.getStatus().trim().equalsIgnoreCase("new")) {
                UserState state = service.verifyUser(email, verifyCode);
                if (state.getMessage().equals(UserState.SUCCESS)) {
                    url = "/Servlet/HomePageServlet";
                    user.setStatus("active");
                    session.setAttribute("identify", user);
                } else {
                    request.setAttribute("message", state.getMessage());
                }
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
