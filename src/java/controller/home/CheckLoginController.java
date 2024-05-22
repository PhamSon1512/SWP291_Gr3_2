/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import config.EncodeData;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author sodok
 */
public class CheckLoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        AccountDAO userdao = new AccountDAO();
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            String name = request.getParameter("name");
            String enpassword = EncodeData.enCode(password);
            Account account = userdao.getAccountByEmail(email);
            if (account == null) {
                request.setAttribute("loginError", "Email hoặc mật khẩu không chính xác!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else if (account.isAcc_status() == false) {
                request.setAttribute("loginError", "Tài khoản đã bị khóa !");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                session.setAttribute("name", name);
                Cookie cemail = new Cookie("email", email);
                Cookie cpass = new Cookie("pass", password);
                Cookie rem = new Cookie("remember", remember);
                if (remember != null) {
                    cemail.setMaxAge(60 * 60 * 24 * 30);
                    cpass.setMaxAge(60 * 60 * 24 * 3);
                    rem.setMaxAge(60 * 60 * 24 * 30);
                } else {
                    cemail.setMaxAge(0);
                    cpass.setMaxAge(0);
                    rem.setMaxAge(0);
                }
                response.addCookie(cemail);
                response.addCookie(cpass);
                response.addCookie(rem);
            }
        }
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
