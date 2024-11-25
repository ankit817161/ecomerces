package com.learn.mycard.servlet;

import com.learn.mycard.dao.UserDao;
import com.learn.mycard.entities.User;
import com.learn.mycard.helper.FactoryProvider;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // create object in userdao class
            //validation or Athontication
            UserDao userDao = new UserDao(FactoryProvider.getFactory());
            User user = userDao.getUserByEmailAndPassword(email, password);

            // object to message commponents
            HttpSession httpSession = request.getSession();
            if (user == null) {
                httpSession.setAttribute("message", "Invalid Details !! Try with another One");
                response.sendRedirect("login.jsp");
                return;

            } else {
                //out.println("<h1>Welcome"+user.getUserType()+"</h1>");
                
                httpSession.setAttribute("current-user", user);
                
                if (user.getUserType().equals("admin")) {
                    // admin user
                    response.sendRedirect("admin.jsp");
                } else if (user.getUserType().equals("normal")) 
                {
                    // normal user 
                    response.sendRedirect("normal.jsp");
                } else 
                {
                    out.println("Invalid user con't access the data");
                }

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
