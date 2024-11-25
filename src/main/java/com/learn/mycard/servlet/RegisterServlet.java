package com.learn.mycard.servlet;

import com.learn.mycard.entities.User;
import com.learn.mycard.helper.FactoryProvider;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                String userName = request.getParameter("user_name");
                String userEmail = request.getParameter("user_email");
                String userPassword = request.getParameter("user_password");
                String userPhone = request.getParameter("user_phone");
                String userAddress = request.getParameter("user_address");
                if (userName.isEmpty()) {
                    //out.println(" this not blanck so plz enter the userName");
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Please Enter !! User Name Not Null" );
                    response.sendRedirect("register.jsp");
                    return;
                }
                // create object classs in User  or using jdbc maybe
                User user = new User(userName, userEmail, userPassword, userPhone, "defoult.jpg", userAddress,"normal");
                Session hibernateSession = FactoryProvider.getFactory().openSession();
                Transaction tx = hibernateSession.beginTransaction();

                int userId = (int) hibernateSession.save(user);

                tx.commit();
                hibernateSession.close();
                // send the page of register 
                HttpSession httpsession = request.getSession();
                httpsession.setAttribute("message", "data successfully store in the db" + userId);
                response.sendRedirect("register.jsp");
                return;

            } catch (Exception e) {
                System.out.println(e);

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
