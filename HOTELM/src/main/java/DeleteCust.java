import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCust extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
	    PrintWriter out=resp.getWriter();
	    resp.addHeader("Access-Control-Allow-Origin","*");
	    try 
	    {
	    	Connection con =DB_Connector.getDbConnection(); 
	    	Integer custid=Integer.parseInt(req.getParameter("custid"));
	  	    Statement st=con.createStatement();
	  	    st.executeUpdate("delete from customer where custid="+custid+";");
            JSONObject jo=new JSONObject();
            jo.put("status","success");
            jo.put("value","deleted successfully");
            out.println(jo.toString());
	    	
	    }
	    catch(Exception e)
	    {
	    	out.println(e); 
	    	
	    }
	}

}

