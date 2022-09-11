import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateRoom extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Integer roomid=Integer.parseInt(req.getParameter("roomid"));
		String status=req.getParameter("status");
		String type=req.getParameter("type");
		Integer price=Integer.parseInt(req.getParameter("price"));
	    PrintWriter out=resp.getWriter();
	    resp.addHeader("Access-Control-Allow-Origin","*");

	    try {
	    	  Connection con =DB_Connector.getDbConnection();
	    	  Statement st=con.createStatement();
	  		  st.executeUpdate("Update room set status='"+status+"', type='"+type+"' , price='"+price+"' where roomid="+roomid+";");
	          JSONObject jo=new JSONObject();
	          jo.put("status","success");
	          out.println(jo.toString());
	    
	    }
	    catch(Exception e)
	    {
	    	out.println(e);
	    }
	    
	}

}
