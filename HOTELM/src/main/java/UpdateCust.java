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

public class UpdateCust extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Integer custid=Integer.parseInt(req.getParameter("custid"));
		String custname=req.getParameter("custname");
		String checkin=req.getParameter("checkin");
		String checkout=req.getParameter("checkout");
		String paymentstatus=req.getParameter("paymentstatus");
		String email=req.getParameter("email");
		Integer phone=Integer.parseInt(req.getParameter("phone"));
	    PrintWriter out=resp.getWriter();
	    resp.addHeader("Access-Control-Allow-Origin","*");

	    try {
	    	  Connection con =DB_Connector.getDbConnection();
	    	  Statement st=con.createStatement();
	  		  st.executeUpdate("Update customer set custname='"+custname+"', checkin='"+checkin+"', checkout='"+checkout+"' , paymentstatus='"+paymentstatus+"' , email='"+email+"' ,phone='"+phone+"'  where custid="+custid+";");
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
