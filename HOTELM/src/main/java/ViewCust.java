
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
public class ViewCust extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              
		PrintWriter out=resp.getWriter();
        resp.addHeader("Access-Control-Allow-Origin","*");
		try {
		    	  Connection con =DB_Connector.getDbConnection();
		    	  Statement st=con.createStatement();
		  		 ResultSet rs=st.executeQuery("Select custid,custname,checkin,checkout,paymentstatus,email,phone from customer");
		  		JSONArray row = new JSONArray();
				while(rs.next())
				{
					   Integer custid=rs.getInt("custid");
					   String custname=rs.getString("custname");
					   String checkin=rs.getString("checkin");
					   String checkout=rs.getString("checkout");
					   String paymentstatus=rs.getString("paymentstatus");
					   String email=rs.getString("email");
					   Integer phone=rs.getInt("phone");
					   JSONObject jo =new JSONObject();
					   jo.put("custid",custid);
					   jo.put("custname",custname);
					   jo.put("checkin",checkin);
					   jo.put("checkout",checkout);
					   jo.put("paymentstatus",paymentstatus);
					   jo.put("email",email);
					   jo.put("phone",phone);
					
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
