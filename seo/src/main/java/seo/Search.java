package seo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;


public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void init(ServletConfig config) throws ServletException {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		String search = (String)request.getParameter("search");
		String seo=null;
		if(search != null)
			seo = search.toLowerCase();
		/*// response.setContentType("application/json");
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
			
		}*/
		
		//String rank=;





	
   
	
    
	
	
		
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("	<head>");
		out.println("		<title>Search Engine</title>");	
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\">");
	    
		 out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css\">");
		    
		 out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>");
		 
		 
		 out.println(" <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">"
		 		+ "<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>"+
				 		"<script src=\"//code.jquery.com/ui/1.11.4/jquery-ui.js\"></script>");
		 
		 
		 
		out.println("	</head>");
		out.println("	<body>");
		
		out.println("<form action = \"\" class=\"form-horizontal\" method = \"get\" role=\"form\">");
		out.println("<h1 align = \"center\">Search Engine</h1>");
		
		out.println("<br/>");
		
		
		
		
		
		
			out.println("<input type = \"text\" placeholder=\"search\"  class=\"form-control\" name= \"search\" id=\"inputSuccess3\"/ >");
		out.println("<br/>");
		out.println("<input type = \"submit\" class=\"btn btn-default\" name= \"submit\" value = \"Search\" / >");
		
		
		out.println("</form>");
		
		
		
		
		
		final InputStream in = new FileInputStream("/Users/rohanpatel/Documents/workspace1/index/target/rank.json");

		Map<String, Double> words = new HashMap<String, Double>();


		try {
			for (Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {
				// System.out.println(it.next());
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> keyValue = (LinkedHashMap<String,Object>) it.next();
			//	LinkedHashMap<String, Integer> fre = (LinkedHashMap<String,Integer>) it.next();
				
				if(seo != null && seo.equals(keyValue.get("word"))){

				String local = (String) keyValue.get("url");
				Double frq = (Double) keyValue.get("Rank");
				
					words.put(local, frq);
					
				
				}
				
				
				
			}
			
			 
			
			Object[] a = words.entrySet().toArray();
		    Arrays.sort(a, new Comparator() {
		        public int compare(Object o1, Object o2) {
		            return ((Map.Entry<String, Double>) o2).getValue().compareTo(
		                    ((Map.Entry<String, Double>) o1).getValue());
		        }
		    });
		
		    if(seo != null){
		
		    out.println("<h4>Search Result</h4>");
		out.println("<table>");
		
		for (Object e : a) {
		       // System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
		             //   + ((Map.Entry<String, Integer>) e).getValue());
		        
				String link = ((Entry<String, Double>) e).getKey().toString();
		    	String r = ((Entry<String, Double>) e).getValue().toString();
		    	
		    	System.out.println(r);
		    	out.println("<tr>");
		    	out.println("<td>");
		    	//out.println("RANK:"+r);
		    	out.println("</td>");
		    	out.println("<td>");
		    	out.println("<div class=\"list-group\">");
		    	out.println("<a href="+link+" class=\"list-group-item active\">"+link+"</a>");
		    	out.println("</div>");
		    	out.println("</td>");
		    	out.println("</tr>");
		      }
		}
		
		out.println("<table>");
		
		
		
		out.println("<script>");
		out.println("$(function() {"
    +"var availableTags = [\"ActionScript\",\"AppleScript\",\"Asp\",\"BASIC\"];");
		
		
		
		
		out.println("</body>");
		out.println("</html>");
}
catch (IOException e1) {
    e1.printStackTrace();

} finally {
    
}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
