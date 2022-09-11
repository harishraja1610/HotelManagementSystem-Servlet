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

public class UpdateBook extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Integer bookid=Integer.parseInt(req.getParameter("bookid"));
		String status=req.getParameter("status");
	    PrintWriter out=resp.getWriter();
	    resp.addHeader("Access-Control-Allow-Origin","*");

	    try {
	    	  Connection con =DB_Connector.getDbConnection();
	    	  Statement st=con.createStatement();
	  		  st.executeUpdate("Update book set status='"+status+"'  where bookid="+bookid+";");
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
