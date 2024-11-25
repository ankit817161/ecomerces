

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login Page</title>
        <%@include file="commponents/com_css_js.jsp" %>
    </head>
    <body>
        <%@include file="commponents/navbar.jsp" %>
        <div class="container ">
            <div class="row">
                <div class="col-md-6 offset-md-4">
                    <div class="card mt-3">
                        <div class="card-header custom-bg text-white text-center">
                            <h3>Login Page</h3>
                            <%@include file="commponents/message.jsp" %>
                        </div>
                        <div class="card-body">
                            <form action="LoginServlet " method="post">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email address</label>
                                    <input type="email"  name="email" class="form-control" id="email" aria-describedby="emailHelp">

                                </div>
                                <div class="mb-3">
                                    <label for="pssword" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                                <div class="container text-center">
                                    <a href="register.jsp">if not register click here</a>
                                </div>
                                <div class="container text-center">
                                    <button type="submit" class="btn btn-primary ">Submit</button>
                                    <button type="reset" class="btn btn-primary ">Reset</button>
                                </div>
                            </form>
                        </div>


                    </div>

                </div> 
            </div>
        </div>


    </body>
</html>
