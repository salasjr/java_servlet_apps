package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Deletemployee
 */
@WebServlet("/deleteurl")
public class Deletemployee extends HttpServlet {
	private static final String query ="Delete from employeelist where id = ?";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletemployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'>"); 

		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
			}
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/newemployee";
			String username = "root";
			String password = "DP9B8xE9%6ibZ.p"; 
			Class.forName(driver); 
			Connection conn = DriverManager.getConnection(url,
			username, password);
			
			PreparedStatement ps = conn.prepareStatement(query);
			
		    ps.setInt(1, id);
		    int count = ps.executeUpdate();
		    if(count == 1) {
		    	pw.println("<div class='alert alert-danger'>");
		    	pw.println("<h2>employee is deleted successfully.</h2>");
		    	pw.println("</div>");

			}else {
		    	pw.println("<div class='alert alert-danger>");
		    	pw.println("<h2>employee not deleted.</h2>");
		    	pw.println("</div>");

			}
			
		}catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
			} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
			
			}
			pw.println("<a href='landingpage.html'>Home</a>");
			pw.print("<br>");
			pw.println("<a href='employeelist'>employee List</a>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
