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
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String r_uname=req.getParameter("uname");
		String r_pwd=req.getParameter("pwd");
		HttpSession session=req.getSession();
		resp.addHeader("Access-Control-Allow-Origin","*");
		PrintWriter out=resp.getWriter();
		Boolean loggedin=false;
		Statement stmt=null;
		try
		{
			Connection con=DB_Connector.getDbConnection();
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from login");
			while(rs.next())
			{
				String uname=rs.getString("uname");
				String pwd=rs.getString("pwd");
			
				if(r_uname.equals(uname) && r_pwd.equals(pwd))
				{
					session.setAttribute("name",uname);
					JSONObject jo=new JSONObject();
					jo.put("status","success");
					jo.put("name",uname);
					out.print(jo.toString());
					loggedin=true;
					break;
				}
				
			}
			if(loggedin==false)
			{
				JSONObject jo=new JSONObject();
				jo.put("status","failed");
				out.print(jo.toString());
			}
		}
		  catch(Exception e)
	      {
			
				out.println(e);
	      }  
		
		
	}

}
