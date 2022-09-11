
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
public class ViewBook extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              
		PrintWriter out=resp.getWriter();
        resp.addHeader("Access-Control-Allow-Origin","*");
		try {
		    	  Connection con =DB_Connector.getDbConnection();
		    	  Statement st=con.createStatement();
		  		 ResultSet rs=st.executeQuery("Select bookid,roomid,custid,status from book");
		  		JSONArray row = new JSONArray();
				while(rs.next())
				{
					   Integer bookid=rs.getInt("bookid");
					   Integer roomid=rs.getInt("roomid");
					   Integer custid=rs.getInt("custid");
					   String status=rs.getString("status");
					   JSONObject jo =new JSONObject();
					   jo.put("bookid",bookid);
					   jo.put("roomid",roomid);
					   jo.put("custid",custid);
					   jo.put("status",status);
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
