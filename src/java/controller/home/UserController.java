/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.home;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;
import dal.*;
import config.*;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 *
 * @author Khuong Hung
 */
@MultipartConfig(maxFileSize = 16177216)
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        AccountDAO userdao = new AccountDAO(); 
        Account user = (Account) session.getAttribute("user");
        String action = request.getParameter("action");
        if (action.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (action.equals("register")) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        if (action.equals("recover")) {
            request.getRequestDispatcher("recover.jsp").forward(request, response);
        }
        if (action.equals("recoverpass")) {
            String type = request.getParameter("type");
            request.setAttribute("type", type);
            request.getRequestDispatcher("recover.jsp").forward(request, response);
        }

        if (action.equals("forgot")) {
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            password = EncodeData.enCode(password);
            userdao.updatePasswordByEmail(email, password);
            request.setAttribute("success", "Thay đổi mật khẩu thành công!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if (action.equals("checkemail")) {
            String email = request.getParameter("email");
            Account account = userdao.getAccountByEmail(email);
            if (account == null) {
                request.setAttribute("email", email);
                request.setAttribute("error", "Email không tồn tại!");
                request.getRequestDispatcher("user?action=recover").forward(request, response);

                if (action.equals("profile")) {
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }

                if (action.equals("updateprofile")) {
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    userdao.changeInformations(email, name, phone);
                    Account a = new Account(user.getRoleID(), name, gender, phone, user.getEmail(), user.getImg(), user.isStatus());
                    session.setAttribute("user", a);
                    request.setAttribute("updatesuccess", "Thông tin đã được cập nhật!");
                    response.sendRedirect("profile.jsp");
                }

                if (action.equals("update_image")) {
                    Part image = request.getPart("image");
                    if (image != null) {
                        try {
                            Account acc = userdao.getAccountByUsername(username);
                            userdao.UpdateImage(username, image);
                            session.setAttribute("user", acc);
                        } catch (Exception e) {
                        }
                    }
                    alert = "success";
                    message = "Cập nhật ảnh thành công";
                    request.setAttribute("alert", alert);
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }

                if (action.equals("changepassword")) {
                    String oldpassword = EncodeData.enCode(request.getParameter("oldpassword"));
                    String newpassword = request.getParameter("newpassword");
                    String renewpassword = request.getParameter("renewpassword");
                    if (!oldpassword.equals(user.getPassword())) {
                        request.setAttribute("oldpassword", EncodeData.deCode(oldpassword));
                        request.setAttribute("newpassword", newpassword);
                        request.setAttribute("renewpassword", renewpassword);
                        request.setAttribute("passerror", "Mật khẩu cũ không đúng!");
                        request.getRequestDispatcher("user?action=profile").forward(request, response);
                    } else {
                        newpassword = EncodeData.enCode(newpassword);
                        userdao.Recover(user.getUsername(), newpassword);
                        request.setAttribute("success", "Thay đổi mật khẩu thành công, mời bạn đăng nhập lại!");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }
        }
    }

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
