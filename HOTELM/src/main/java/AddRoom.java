import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddRoom extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Integer roomid=Integer.parseInt(req.getParameter("roomid"));
		String status=req.getParameter("status");
		String type=req.getParameter("type");
		Integer price=Integer.parseInt(req.getParameter("price"));
	    resp.addHeader("Access-Control-Allow-Origin","*");
	    PrintWriter out=resp.getWriter();
	    try {
	    	  Connection con =DB_Connector.getDbConnection();
	          PreparedStatement ps=con.prepareStatement("insert into room(roomid,status,type,price)values(?,?,?,?)"); 
	   	            ps.setInt(1,roomid);
	    		    ps.setString(2,status);
	        		ps.setString(3,type);
	        		ps.setInt(4,price);
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



