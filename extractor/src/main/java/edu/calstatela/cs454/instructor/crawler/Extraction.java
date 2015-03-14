package edu.calstatela.cs454.instructor.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

public class Extraction {

	 public void extract(String c) throws IOException, SAXException, TikaException {
		 
		 
		// String cf = "D:/crawler3-app/target/"+c;
			//InputStream in = new ByteArrayInputStream(cf.getBytes("UTF-8"));
		 
		 
		 final InputStream in = new FileInputStream("/Users/rohanpatel/Documents/workspace1/crawler3-app/target/"+c);
		 
				try {
					for (Iterator it = new ObjectMapper().readValues(
							new JsonFactory().createJsonParser(in), Map.class); it
							.hasNext();) {
						// System.out.println(it.next());
						@SuppressWarnings("unchecked")
						LinkedHashMap<String, File> keyValue = (LinkedHashMap<String,File>) it.next();
						//System.out.println(keyValue.get("storage"));
			
						try{
							BodyContentHandler handler = new BodyContentHandler();
						      Metadata metadata = new Metadata();
						      FileInputStream inputstream = new FileInputStream( "/Users/rohanpatel/Documents/workspace1/crawler3-app/target/"+keyValue.get("storage"));
						      ParseContext pcontext = new ParseContext();
						      AutoDetectParser  msofficeparser = new AutoDetectParser(); 
						      msofficeparser.parse(inputstream, handler, metadata,pcontext);
						      
						      JSONObject obj = new JSONObject();
						      
						      obj.put("path", "/Users/rohanpatel/Documents/workspace1/crawler3-app/target/"+keyValue.get("storage"));
						        /*  obj.put("Title",metadata.get("title") );
						          obj.put("url", metadata.get("og:url"));
						          obj.put("Content-Type", metadata.get("Content-Type"));*/
						         // obj.put("keywords", metadata.get("keywords"));
						        //  obj.put("Description", metadata.get("description"));
						          for(int i = 0; i <metadata.names().length; i++) { 
						        	  String name = metadata.names()[i]; 
						        	  obj.put(name,metadata.get(name));
						        //  System.out.println(name + " : " + metadata.get(name)); 
						        	  }
						        
					          
					         
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
	                     catch(RuntimeException e){
							
						}
						
					}
			
				}
			
			
			//new Extraction().extract();
		
					finally {
						in.close();
					}
				         
		       // System.out.println(file.getCanonicalPath());
		    }
		 
		 
			
	 }
		
			
			
	 
			 
		
		
			
		 
		     
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		  

		


