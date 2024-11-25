<%@page import="java.util.ArrayList"%>
<%@page import="com.learn.mycard.helper.Helper"%>
<%@page import="com.learn.mycard.entities.Category"%>
<%@page import="com.learn.mycard.dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@page import="com.learn.mycard.entities.Product"%>
<%@page import="com.learn.mycard.dao.ProductDao"%>
<%@page import="com.learn.mycard.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My card</title>
        <%@include file="commponents/com_css_js.jsp" %>
    </head>
    <body>

        <%@include file="commponents/navbar.jsp" %>

        <div class="row mt-3 mx-2">
            <%                // this is all product 
                String cat = request.getParameter("category");
                //out.println(cat);
                ProductDao dao = new ProductDao(FactoryProvider.getFactory());
                //List<Product> list =null;
                List<Product> list = new ArrayList<>();

                if (cat == null||cat.trim().equals("all")) {
                  list=dao.getAllProducts();
                } else {
                        int  cid = Integer.parseInt(cat.trim());
                        list = dao.getAllProductsById(cid);
                      
                }

                CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                List<Category> clist = cdao.getCategorise();

            %>

            <!-- show category -->
            <div class="col-md-2">
                <div class="list-group">
                    <a href="index.jsp?category=all" class="list-group-item list-group-item-action active" aria-current="true">
                        All Category
                    </a>
                    <%                     for (Category c : clist) {
                    %>

                    <a href="index.jsp?category=<%=c.getCategoryId()%>" class="list-group-item list-group-item-action"> <%= c.getCategoryTitle()%></a>
                    <%
                        }

                    %>


                </div>
            </div>


            <!-- show Product  -->
            <div class="col-md-10">
                <!-- row -->
                <div class="row mt-4">
                    <!-- col-12 -->


                    <!-- traversing the product -->
                    <%                        for (Product p : list) {
                    %>
                    <div class="col-sm-4 text-center mb-4 ">
                        <div class="card " style="width: 22rem; product-card">
                            <img src="img/products/<%= p.getpPhoto()%>" class="card-img-top" height="300" alt="...">
                            <div class="card-body">
                                <h5 class="card-title"><%= p.getpName()%></h5>
                                <p class="card-text">
                                    <%= Helper.get10Words(p.getpDesc())%>   
                                </p>

                            </div>
                            <div class="card-footer text-center">
                                <button class="btn custom-bg text-white" onclick="add(<%= p.getpId() %>,'<%=p.getpName() %>',<%=p.getPriceAfterDiscount() %>)">Add to card</button>
                                <button class="btn btn-outline-primary">&#8377; <%= p.getPriceAfterDiscount()%>/- <span class="text-secondary discount-lable"><%= p.getpPrices()%> , <%=p.getpDiscount() %>% off </span></button>

                            </div>
                        </div>
                    </div>

                    <%}
                        if (list.size() == 0){
                            out.println(" ! No items");
                        }

                    %>


                </div>

            </div>
        </div>
                   <%@include file="commponents/modal.jsp" %>
                    
    </body>
</html>
