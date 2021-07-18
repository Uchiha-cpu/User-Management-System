<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page import="java.io.PrintWriter"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.PreparedStatement"%>
	<%@page import="java.sql.Connection"%>
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
                        <a> User Management App </a>
                    </div>
                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                    <!-- <div class="col-md-4">
					     <form action="" method="get">
							<input type="text"class="form-control" name="q" placeholder="Search here...."/>
						</form>
					</div> -->
                </nav>
            </header>
            <br>
			
			<div class="container">
            
            
                    <div class="form-group">
                        <h2>Find City</h2>
                    </div>
                <br>
                <div class="form-group">
            <form method="get" action="searching">     
                      <div class="form-group">
                          <h3>Select the Country</h3>
       <%
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
         try
		{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false","root","123456789");
            pst = con.prepareStatement("select distinct country from users");
	    	rs=pst.executeQuery();
            
            if(rs.next())
	   		{
	      		out.println("<tr>");
              	out.println("<td>Choose Country Name</td>");
	     		out.println("<td>");
	      		out.println("<select name='combo'>");
	  			do{
           			 String countryname = rs.getString("country");
	    			out.println("<option value='"+ countryname   +"'>" +  countryname +  "</option>");
          			}
           		 while(rs.next());
	   		    out.println("</select>");
           		rs.close();
	   			out.println("</td></tr>");
           		out.println("</tr><td colspan=2 align=center><input type=submit  value=Retrieve></td></tr>");
           		}
           	else
            {
           		out.println("<tr>");
 	  		 	out.println("<td colspan=2 align=right>");
	   			out.println("Sorry table is Empty");
           		out.println("</td>");
            }	
 
	   		out.println("</form>");
	  		}
        	catch(Exception e)
        	{
                 e.printStackTrace(); // Or System.out.println(e);
        	}	 
                              
        %>
         </div>
                    
           
                    
          </form>                     
                    
              </div>      
               
     </div>
</div>
			
            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Users</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
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
                            <c:forEach var="user" items="${listUser}">

                                <tr>
                                    <td>
                                        <c:out value="${user.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.country}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>