

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register page</title>
        <%@include file="commponents/com_css_js.jsp" %>
    </head>
    <body>
        <%@include file="commponents/navbar.jsp" %>
        <div class="row mt-5">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                   <%@include file="commponents/message.jsp" %>
                    <div class="card-body px-5">
                        <div class=" container text center">
                            <i class="fa-solid fa-user"></i>
                        </div>
                     
                        <h3 class="text-center my-3"> Sign Up here !! </h3>
                        <form action="RegisterServlet" method="post">
                            <div class="form-group">
                                <label for="name">User Name</label>
                                <input  class="form-control" type="text" name="user_name"id="name" placeholder="Enter uers name" aria-describedby="emailhelp">
                            </div>

                            <div class="form-group">
                                <label for="email">User Email</label>
                                <input  class="form-control" type="email" name="user_email"id="email" placeholder="Enter uers email" aria-describedby="emailhelp">
                            </div>
                            <div class="form-group">
                                <label for="password">User Password</label>
                                <input  class="form-control" type="password" name="user_password"id="password" placeholder="Enter uers password" aria-describedby="emailhelp">
                            </div>
                            <div class="form-group">
                                <label for="phone">User Phone</label>
                                <input  class="form-control" type="phone" name="user_phone"id="phone" placeholder="Enter uers phone" aria-describedby="emailhelp">
                            </div>


                            <div class="form-group">
                                <label for="address">User Address</label>
                                <textarea style="height: 200px"  name="user_address" class="form-control" placeholder="Enter uers address"aria-describedby="emailhelp"></textarea>  
                            </div>

                            <div class="container text-center"> 
                                <button  type="submit" class="btn btn-outline-success"> Register</button>
                                <button type="reset" class="btn btn-outline-warning"> Reset</button>

                            </div>


                        </form>
                    </div>
                </div>


            </div>


        </div>



    </body>
</html>
