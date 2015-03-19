package search_engine;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.google.gson.Gson;

import java.util.List;


@WebServlet("/search_engine/Front_end")
public class Front_end extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String search = request.getParameter("search");
		
		// response.setContentType("application/json");
		String json = 
	            "{"
	                + "'title': 'Computing and Information systems',"
	                + "'id' : 1,"
	                + "'children' : 'true',"
	                + "'groups' : [{"
	                    + "'title' : 'Level one CIS',"
	                    + "'id' : 2,"
	                    + "'children' : 'true',"
	                    + "'groups' : [{"
	                        + "'title' : 'Intro To Computing and Internet',"
	                        + "'id' : 3,"
	                        + "'children': 'false',"
	                        + "'groups':[]"
	                    + "}]"
	                + "}]"
	            + "}";
     
		//Gson r = new Gson();
		Data data = new Gson().fromJson(json, Data.class);
		
		 System.out.println(data);
		
		
		if(search != null){
			
		}
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("	<head>");
		out.println("		<title>CS320 Session Countdown</title>");	
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\">");
	    
		 out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css\">");
		    
		 out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>");
		out.println("	</head>");
		out.println("	<body>");
		
		out.println("<form action = \"Login\" class=\"form-horizontal\" method = \"get\" role=\"form\">");
		out.println("<h1 align = \"center\">Search Engine</h1>");
		
		out.println("<br/>");
		
		
		
		
		
		
			out.println("<input type = \"text\" placeholder=\"search\"  class=\"form-control\" name= \"search\" id=\"inputSuccess3\"/ >");
		out.println("<br/>");
		out.println("<input type = \"submit\" class=\"btn btn-default\" name= \"submit\" value = \"Search\" / >");
		
		
		out.println("</form>");
		
		
		out.println("</body>");
		out.println("</html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
