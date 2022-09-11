import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCust extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Integer custid=Integer.parseInt(req.getParameter("custid"));
		String custname=req.getParameter("custname");
		String checkin=req.getParameter("checkin");
		String checkout=req.getParameter("checkout");
		String paymentstatus=req.getParameter("paymentstatus");
		String email=req.getParameter("email");
		Integer phone=Integer.parseInt(req.getParameter("phone"));
	    resp.addHeader("Access-Control-Allow-Origin","*");
	    PrintWriter out=resp.getWriter();
	    try {
	    	  Connection con =DB_Connector.getDbConnection();
	          PreparedStatement ps=con.prepareStatement("insert into customer(custid,custname,checkin,checkout,paymentstatus,email,phone)values(?,?,?,?,?,?,?)"); 
	   	            ps.setInt(1,custid);
	    		    ps.setString(2,custname);
	        		ps.setString(3,checkin);
	        		ps.setString(4,checkout);
	        		ps.setString(5,paymentstatus);
	        		ps.setString(6,email);
	        		ps.setInt(7,phone);
	        		ps.executeUpdate();
	        		JSONObject jo=new JSONObject();
	        		jo.put("Status","success");
	        		jo.put("value","Data inserted successfully");
	        		out.println(jo.toString());
	          }
	          catch(Exception e)
	          {
	        	  out.println(e);
	          }
	    }
	}

