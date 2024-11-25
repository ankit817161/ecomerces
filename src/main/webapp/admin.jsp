<%@page import="java.util.List"%>
<%@page import="com.learn.mycard.entities.Category"%>
<%@page import="com.learn.mycard.helper.FactoryProvider"%>
<%@page import="com.learn.mycard.dao.CategoryDao"%>
<%@page import="com.learn.mycard.entities.User"%>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! login First");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user.getUserType().equals("normal")) {
            session.setAttribute("message", "You are not admin ! Do not access this page");
            response.sendRedirect("login.jsp");
            return;
        }
    }


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin</title>
        <%@include file="commponents/com_css_js.jsp" %>
    </head>
    <body style="background: url(img/back.jpg);background-size: cover;background-attachment: fixed">
        <%@include file="commponents/navbar.jsp" %>

        <div class="container admin ">
            <div class="container-fluid mt-3">
                <%@include file="commponents/message.jsp" %>
            </div>
            <div class="row mt-4">
                <!-- first col -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body  text-center">
                            <div class="container">
                                <img  style="max-width: 100px" class="img-fluid rounded-circle" src="img/team.png" alt ="user_icon">
                            </div>
                            <h1> 24564</h1>
                            <h1> USER</h1>
                        </div>
                    </div>
                </div>

                <!-- second col -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body  text-center">
                            <div class="container">
                                <img  style="max-width: 100px" class="img-fluid rounded-circle" src="img/categary.png" alt ="user_icon">
                            </div>
                            <h1> 23455</h1>
                            <h1> CATEGORY</h1>
                        </div>
                    </div>
                </div>
                <!-- thried col -->
                <div class="col-md-4  ">
                    <div class="card">
                        <div class="card-body  text-center">
                            <div class="container">
                                <img  style="max-width: 100px" class="img-fluid rounded-circle" src="img/delivery-box.png" alt ="user_icon">
                            </div>
                            <h1> 45342</h1>
                            <h1> PRODUCT</h1>
                        </div>
                    </div>
                </div>
            </div>
            <!-- second  row -->
            <div class="container">
                <div class="row mt-2 ">

                    <!-- second  col  -->
                    <div class="col-md-6  ">
                        <div class="card" data-bs-toggle="modal" data-bs-target="#add-category-moadal">
                            <div class="card-body  text-center">
                                <div class="container">
                                    <img  style="max-width: 110px" class="img-fluid " src="img/add-friend.png" alt ="user_icon">
                                </div>
                                <P class="mt-2">click here add to new category</P>
                                <h1 class="text-uppercase text-mute">Add category</h1>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-6  ">
                        <div class="card " data-bs-toggle="modal" data-bs-target="#add-product-moadal">
                            <div class="card-body  text-center">
                                <div class="container">
                                    <img  style="max-width: 100px" class="img-fluid" src="img/add-product.png" alt ="user_icon">
                                </div>
                                <P class="mt-2">click here add to new Product</P>
                                <h1 class="text-uppercase text-mute"> Add Product</h1>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </div>
        <!-- Start Modal -->


        <!-- Modal -->
        <div class="modal fade" id="add-category-moadal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header custom-bg text-white text-center">
                        <h1 class=" modal-title fs-5 " id="exampleModalLabel">Add Category</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="ProductOperationServlet" method="post">
                            <input type="hidden" name="operation" value="addcategory">
                            <div class="form-group">

                                <input type="text" class="form-control" name="catTitle" placeholder="Enter category title" required/>
                            </div>
                            <div class=" form-group mt-2">
                                <textarea  style=" height: 200px" name="catDescription" class="form-control" placeholder="Enter category description" required ></textarea>

                            </div>
                            <div class="container text-center">

                                <button type="submit" class="btn btn-outline-success">Add category</button>
                                <button type="button" class="btn btn-secondry">Close</button>
                            </div>


                        </form>
                    </div>

                </div>
            </div>
        </div>


        <!--  End Modal -->






        <!--  start 2 Modal -->
        <div class="modal fade" id="add-product-moadal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header custom-bg text-white">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Add Product</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Start form Add product -->
                        <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="operation" value="addproduct" />
                            <div class="form-group">

                             <input type="text" class="form-control" name="pName" placeholder="Enter product name" required/>
                            </div>
                            <div class=" form-group mt-2">
                                <textarea  style=" height: 200px" name=" pDesc" class="form-control" placeholder="Enter product description" required ></textarea>

                            </div>
                            <div class="form-group mt-2">

                                <input type="text" class="form-control" name="pPrices" placeholder="Enter product Prices" required/>
                            </div>
                            <div class="form-group mt-2">

                                <input type="text" class="form-control" name="pDiscount" placeholder="Enter product Discount" required/>
                            </div>

                            <div class="form-group mt-2">

                                <input type="text" class="form-control" name="pQuantity" placeholder="Enter product Quantity" required/>
                            </div>

                            <%   CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                                List<Category> list = cdao.getCategorise();
                            %>
                            <div class="form-group mt-2">
                                <select name=" catId" class="form-control" >
                                    <%
                                        for (Category c : list) {
                                    %>
                                    <option value="<%=c.getCategoryId()%>"><%= c.getCategoryTitle()%> </option>

                                    <%  }%>




                                </select>
                            </div>
                            <div class="form-group mt-1">
                                <label for="pPhoto"> Select picture of product</label>
                                <input type="file" class="form-control"  id="pPhoto" name="pPhoto" placeholder="Enter Select Photo" required/>
                            </div>

                            <div class="container text-center">

                                <button type="submit" class="btn btn-outline-success">Add Product</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>





                        </form>

                    </div>

                </div>
            </div>
        </div>


        <!--  End second  Modal -->
    </body>
</html>
