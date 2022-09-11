import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddBook extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Integer bookid=Integer.parseInt(req.getParameter("bookid"));
		Integer roomid=Integer.parseInt(req.getParameter("roomid"));
		Integer custid=Integer.parseInt(req.getParameter("custid"));
		String status=req.getParameter("status");
	    resp.addHeader("Access-Control-Allow-Origin","*");
	    PrintWriter out=resp.getWriter();
	    try {
	    	  Connection con =DB_Connector.getDbConnection();
	          PreparedStatement ps=con.prepareStatement("insert into book(bookid,roomid,custid,status)values(?,?,?,?)"); 
	   	            ps.setInt(1,bookid);
	    		    ps.setInt(2,roomid);
	        		ps.setInt(3,custid);
	        		ps.setString(4,status);
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
