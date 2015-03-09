package edu.calstatela.cs454.instructor.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

public class Extraction {

	 public void extract() throws IOException {
		 
		 
		 File f = new File("E:/Storage"); // current directory

		    File[] files = f.listFiles();
		    for (File file : files) {
		    	try{
					BodyContentHandler handler = new BodyContentHandler();
				      Metadata metadata = new Metadata();
				      FileInputStream inputstream = new FileInputStream(file);
				      ParseContext pcontext = new ParseContext();
				      AutoDetectParser  msofficeparser = new AutoDetectParser(); 
				      msofficeparser.parse(inputstream, handler, metadata,pcontext);
				      
				      JSONObject obj = new JSONObject();
			          obj.put("Title",metadata.get("title") );
			          obj.put("url", metadata.get("og:url"));
			          obj.put("Content-Type", metadata.get("Content-Type"));
			          obj.put("keywords", metadata.get("keywords"));
			          obj.put("Description", metadata.get("description"));
			          
			         
			          File f2 = new File("file2.json");
			          BufferedWriter file2 = new BufferedWriter(new FileWriter(f2,true)); 
			            try {
			            	
			            	ObjectMapper mapper = new ObjectMapper();
			               file2.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
			            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
			                file2.newLine();
			                file2.newLine();
			                file2.newLine();
			                file2.newLine();
			               
			     
			            } catch (IOException e) {
			                e.printStackTrace();
			     
			            } finally {
			                file2.flush();
			                file2.close();
			            }
			            
					}
					catch(FileNotFoundException e){
						
					}
					catch(Exception e){
						
					}
				         
		       // System.out.println(file.getCanonicalPath());
		    }
		 
		 
			
	 }
}
			
			
			
	 
			 
		
		
			
		 
		     
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		  

		


