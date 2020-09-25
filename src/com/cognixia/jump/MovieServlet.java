package com.cognixia.jump;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MovieServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3221423500005739013L;

	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private PreparedStatement pstmt4;
	private PreparedStatement pstmt5;
	private ResultSet rs;
	
	
	@Override
	public void init() {
		
		 conn=ConnectionManager.getConnection();	
		 try {
			pstmt=conn.prepareStatement(" select title,  description, rating from film where rental_rate = ?  and rating = ? limit ? ");
			pstmt2=conn.prepareStatement(" select title, description, rating from film where rental_rate = ?  and rating in (?, ?) limit ? ");
			pstmt3=conn.prepareStatement(" select title, description, rating from film where rental_rate = ?  and rating in (?, ?, ?) limit ? ");
			pstmt4=conn.prepareStatement(" select title, description, rating from film where rental_rate = ?  and rating in (?, ?, ?, ?) limit ? ");
			pstmt5=conn.prepareStatement(" select title, description, rating from film where rental_rate = ?  limit ? ");
      		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		List<Movie> movies = new ArrayList<>();
		List<String>ratingList=new ArrayList<>();
		Double double1=0.0;
		
		try {
		
		
       
		
	    String choice = request.getParameter("rental");
		if (choice.equals("0.99")) {
			double1=0.99;	
		}
		else if(choice.equals("2.99")) {
			double1=2.99;;
		}
		else if(choice.equals("4.99")) {
			double1=4.99;;
		}
		
	   
        
	    if (request.getParameter("G")!=null) {
	    	ratingList.add("G");
		}
	    
	    if (request.getParameter("PG")!=null) {
	    	ratingList.add("PG");
		}
	    if (request.getParameter("PG-13")!=null) {
	    	ratingList.add("PG-13");
		}
	    if (request.getParameter("NC-17")!=null) {
	    	ratingList.add("NC-17");
		}
	    if(request.getParameter("R")!=null) {
	    	ratingList.add("R");
	    }
	    else {
			System.out.println("bosver");
		}
	    
	    int number1=Integer.parseInt(request.getParameter("result"));
	    
			
		if (ratingList.size()==1) {
			pstmt.setString(2, ratingList.get(0));
			pstmt.setInt(3, number1);
			pstmt.setDouble(1, double1);
			rs=pstmt.executeQuery();
		}
		else if (ratingList.size()==2) {
			pstmt2.setString(2, ratingList.get(0));
			pstmt2.setString(3, ratingList.get(1));
			pstmt2.setDouble(1, double1);
			pstmt2.setInt(4, number1);
			rs=pstmt2.executeQuery();
		}
		else if (ratingList.size()==3) {
			pstmt3.setString(2, ratingList.get(0));
			pstmt3.setString(3, ratingList.get(1));
			pstmt3.setString(4, ratingList.get(2));
			pstmt3.setDouble(1, double1);
			pstmt3.setInt(5, number1);
		    rs=pstmt3.executeQuery();
		}
		else if (ratingList.size()==4) {
			pstmt4.setString(2, ratingList.get(0));
			pstmt4.setString(3, ratingList.get(1));
			pstmt4.setString(4, ratingList.get(2));
			pstmt4.setString(5, ratingList.get(3));
			pstmt4.setDouble(1, double1);
			pstmt4.setInt(6, number1);
		    rs=pstmt4.executeQuery();
		}
		else if (ratingList.size()==5) {
			pstmt5.setDouble(1, double1);
			pstmt5.setInt(2, number1);
			rs=pstmt5.executeQuery();
		}
		
		
	    
//		
//		if (rs.next()) {
//	     	System.out.println("yes");
//		}
//		else  {
//			System.out.println("no");
//		}
	     
	    
		while (rs.next()) {
			Movie movie =new Movie(rs.getString("title"), rs.getString("description"),rs.getString("rating"));
			movies.add(movie);
		}
	    
				
		 rs.close();  
		System.out.println("ratingsize " +ratingList.size());
		 System.out.println("size " + movies.size());
	}
	
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (NullPointerException e) {
	e.printStackTrace();
	}
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<html>");
		pw.println("<header><title>Movie</title></header");
		pw.println("<body>");
		pw.println("<h1>" + "Movies" + "</h1>");
		for (int i = 0; i < movies.size(); i++) {
			pw.println("<p style='color:blue;'>" +(i+1) +" ) " +movies.get(i) + "<p>");
			//pw.println("<h1>" + movies.get(i) + "</h1>");
		}
		
		
		pw.println("</body>");
		pw.println("</html>");
	
		
		
		
		
	     	
	}	
	
	@Override
	public void destroy() {
		try {
			pstmt.close();
			conn.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	


}