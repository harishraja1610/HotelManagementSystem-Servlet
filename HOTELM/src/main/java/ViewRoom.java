
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class ViewRoom extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              
		PrintWriter out=resp.getWriter();
        resp.addHeader("Access-Control-Allow-Origin","*");
		try {
		    	  Connection con =DB_Connector.getDbConnection();
		    	  Statement st=con.createStatement();
		  		 ResultSet rs=st.executeQuery("Select roomid,status,type,price from room");
		  		JSONArray row = new JSONArray();
				while(rs.next())
				{
					   Integer roomid=rs.getInt("roomid");
					   String status=rs.getString("status");
					   String type=rs.getString("type");
					   Integer price=rs.getInt("price");
					   JSONObject jo =new JSONObject();
					   jo.put("roomid",roomid);
					   jo.put("status",status);
					   jo.put("type",type);
					   jo.put("price",price);
					   row.put(jo);
				}
				out.println(row);
		}
		catch(Exception e)
	    {
	         out.println(e);
	    }
	}
}
