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
                  
                </nav>
            </header>
            <br>
			
		
         </div>
                    
           
                    
          </form>                     
                    
              </div>      
               
     </div>
</div>
			
            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center"> Users</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
                        <a href="<%=request.getContextPath()%>/list" class="btn btn-success">Users</a>
                    	<a href="<%=request.getContextPath()%>/searching" class="btn btn-success">Search</a>
                    	<a href="<%=request.getContextPath()%>/clearall" class="btn btn-success">Clearall</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Country</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                         
                            <!--   for (Todo todo: todos) {  -->
                            <c var="existingUser" items="${listUser1}">

                                <tr>
                                    <td>
                                        <c:out value="${existingUser.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${existingUser.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${existingUser.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${existingUser.country}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${existingUser.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${existingUser.id}' />">Delete</a></td>
                                </tr>
                            </c>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>