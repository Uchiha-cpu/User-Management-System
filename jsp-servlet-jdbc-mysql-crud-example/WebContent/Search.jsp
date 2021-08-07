<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
            crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                       <a> User Management Application </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">

                            <form action="searching" method="GET">

                        <caption>
                            <h2>
                                    Search User  
                            </h2>
                        </caption>

 						<fieldset class="form-group">
                            <label>ID</label> <input type="text" value="<c:out value='${user.id}' />" class="form-control" name="id" >
                        </fieldset>
                        <fieldset class="form-group">
                            <label>User Name</label> <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Email</label> <input type="text" value="<c:out value='${user.email}' />" class="form-control" name="email" >
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Country</label> <input type="text" value="<c:out value='${user.country}' />" class="form-control" name="country">
                        </fieldset>
                        <button type="submit" class="btn btn-success">Search</button>
						<a href="<%=request.getContextPath()%>/list1" class="btn btn-success">view searched</a>
                        
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>