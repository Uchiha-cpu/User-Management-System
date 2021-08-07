package net.usermanagement.web;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.usermanagement.dao.UserDAO;
import net.usermanagement.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private List<User> existingUser;
	public void init() {
		userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		  String action = request.getServletPath();

		/*try {
			
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
				if(request.getServletPath()=="/searching") {
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
			case "/clearall":
				clear(request,response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/list1":
				listUser1(request,response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	 
	
		       
	    
	private void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("user-list.jsp");
		
		dispatcher1.forward(request, response);
	}
	

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listUser1(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setAttribute("listUser1", existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Searchvalue.jsp");
		dispatcher.forward(request, response);
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");
		int id=request.getIntHeader("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(id,name, email, country);
		 existingUser=userDAO.search(newUser);		
		/*
		 * if(existingUser.getName()==newUser.getName()) { PrintWriter
		 * output=response.getWriter(); output.
		 * println("<html><head></head><body onload=\"alert('User is present')\"></body></html>"
		 * ); }
		 */		 
		 //request.setAttribute("listUser1",existingUser);
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
