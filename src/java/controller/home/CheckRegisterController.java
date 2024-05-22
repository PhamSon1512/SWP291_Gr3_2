/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import config.EncodeData;
import config.Validate;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author sodok
 */
public class CheckRegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AccountDAO userdao = new AccountDAO();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String name = request.getParameter("name");
        String rphone = request.getParameter("phone");
        int role_id = 0;
        String img = "default";
        boolean status = true;
        String enpassword = EncodeData.enCode(password);
        int phone = Integer.parseInt(rphone);
        String fullname = Validate.capitalizeFirstLetter(name);
        Account account = userdao.getAccountByEmail(email);
        if (account != null) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("repassword", repassword);
            request.setAttribute("name", fullname);
            request.setAttribute("phone", phone);
            request.setAttribute("error", "Email hoặc username đã tồn tại trên hệ thống!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (password.equals(repassword)) {
            userdao.insertAccount(fullname, email, rphone, enpassword, role_id, 1, "");
            request.setAttribute("name", fullname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("password", enpassword);
            request.setAttribute("roleID", role_id);
            request.setAttribute("success", "Bạn đã đăng kí thành công !!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);

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
