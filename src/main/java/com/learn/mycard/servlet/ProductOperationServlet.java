package com.learn.mycard.servlet;

import com.learn.mycard.dao.CategoryDao;
import com.learn.mycard.dao.ProductDao;
import com.learn.mycard.entities.Category;
import com.learn.mycard.entities.Product;
import com.learn.mycard.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // add category
            // add product
            String op = request.getParameter("operation");
            if (op.trim().equals("addcategory")) {
                //add category
                String title = request.getParameter("catTitle");
                String description = request.getParameter("catDescription");
                //  save category in db using hibernateor jdbc but i am using CategoryDao class

                Category category = new Category();
                category.setCategoryTitle(title);
                category.setCategoryDescription(description);

                // create object in categoryDao class
                CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
                int catId = categoryDao.saveCategory(category);
                //out.println("Category Add successfully");

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Category Add successfully");
                response.sendRedirect("admin.jsp");
                return;

            } else if (op.trim().equals("addproduct")) {
                //add product in the db              
                String name = request.getParameter("pName");
                String desc = request.getParameter("pDesc");
                int price = Integer.parseInt(request.getParameter("pPrices"));
                int discount = Integer.parseInt(request.getParameter("pDiscount"));
                int quantity = Integer.parseInt(request.getParameter("pQuantity"));
                int catId = Integer.parseInt(request.getParameter("catId"));
                Part part = request.getPart("pPhoto");

                Product p = new Product();
                p.setpName(name);
                p.setpDesc(desc);
                p.setpPrices(price);
                p.setpDiscount(discount);
                p.setpQuantity(quantity);
                p.setpPhoto(part.getSubmittedFileName());

                // get value of catId object to  category 
                CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                Category category = cdao.getCategoryById(catId);
                out.println(category);
                p.setCategory(category);
                
                ///save product in databases
                ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
                pdao.saveProduct(p);
                // upload image

                try   
                {
                   String path = request.getServletContext().getRealPath("img")+ File.separator + "products"+File.separator+part.getSubmittedFileName();
                   System.out.println(path);
                    // uploaded code.....
                    FileOutputStream fos = new FileOutputStream(path);
                    InputStream is = part.getInputStream();
                    // read data .....
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    // write the data
                    fos.write(data);
                    fos.close();
                }catch(Exception e){
                   System.out.println(e);
                }
               
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Product  Add successfully");
                response.sendRedirect("admin.jsp");
                return;

            } else {

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
