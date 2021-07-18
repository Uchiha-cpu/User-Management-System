package net.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.usermanagement.dao.UserDAO;
import net.usermanagement.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	int i = 0;
	public void init() {
		userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*String action = request.getServletPath();

		try {
			
			if(request.getServletPath()=="/new") {
				showNewForm(request, response);
			}
			else
				if(request.getServletPath()=="/insert") {
				insertUser(request, response);
				}
			else
				if(request.getServletPath()== "/delete") {
				deleteUser(request, response);
				}
			else
				if(request.getServletPath()=="/edit") {
				showEditForm(request, response);
				}
			else
				if(request.getServletPath()=="/update") {
				updateUser(request, response);
				}
				else
				listUser(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}*/
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/searching":
				search(request,response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	 
	  private void search(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	    {
		  
		  PreparedStatement pst;
		  ResultSet rs;
		  Connection con;
	        try {
	            i++;
	            PrintWriter out = response.getWriter();
	            response.setContentType("text/html");
	            String countryname= request.getParameter("combo");
	 
				
				  Class.forName("com.mysql.jdbc.Driver");
				  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false","root","123456789");
				 
	            pst = con.prepareStatement("select id,name,email,country from users where country =?");
	            pst.setString(1, countryname);
	            rs = pst.executeQuery();
	             
	             out.println("<table width=60% border= 1   >");
	             out.println("<tr><td colspan=4 " );
	             out.println("<center><h2>Result of Search Page</h2></center>");	
	             out.println("</td></tr>");
	             out.println("<tr>");
	             out.println("<th>User id</th>");
	             out.println("<th>User name</th>");
	             out.println("<th>Email</th>");
	             out.println("<th>Country</th>");
	             out.println("</tr>");
	              
	              while(rs.next())
	              {
	                  out.println("<tr>");
	                  out.println("<td>" + rs.getString("id") + "</td> ");
	                  out.println("<td>" + rs.getString("name") + "</td> ");
	                  out.println("<td>" + rs.getString("email") + "</td> ");
	                  out.println("<td>" + rs.getString("country") + "</td> ");
	                  out.println("</tr>");
	 
	                  
	              }
	              out.println("</table>");
	        
	               } 
	            catch (ClassNotFoundException ex) {
	           
	        }catch (Exception e) 
	        { throw new ServletException("error", e); }
	    }
	        public void destory()
	        {
	            i = 0;
	        }
	     
		       
	    
	    
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		User book = new User(id, name, email, country);
		userDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}

}
