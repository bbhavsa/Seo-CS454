package edu.calstatela.cs454.instructor.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.jackson.JsonFactory;
import org.json.simple.JSONObject;
import org.xml.sax.ContentHandler;
import org.codehaus.jackson.map.ObjectMapper;

public class Index {
	@SuppressWarnings("unchecked")
	public void index(String c, String stw, String index, String rank) throws IOException{
		

	/* File f = new File("D:/Storage"); // current directory

	   File[] files = f.listFiles();
	    for (File file : files) {*/
		final InputStream in = new FileInputStream("/Users/rohanpatel/Documents/workspace1/extractor/target/"+c);
		 
		try {
			for (Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {
				// System.out.println(it.next());
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, String> keyValue = (LinkedHashMap<String,String>) it.next();
				System.out.println(keyValue.get("path"));
	
	    	try{
	    		
	    		 InputStream input = new FileInputStream(keyValue.get("path"));
	    	        ContentHandler handler = new BodyContentHandler();
	    	        Metadata metadata = new Metadata();
	    	        new HtmlParser().parse(input, handler, metadata, new ParseContext());
	    	        String plainText = handler.toString();
	    	       // System.out.println(plainText);
	    	        JSONObject obj = new JSONObject();
	    	        
	    	        String[] stringArray = plainText.split("\\s+");
	    	        List<String> wordList = Arrays.asList(stringArray);
	    	        
	    	        
	    	        
	    	        
	    	        
	    	        obj.put("link",keyValue.get("path"));
	    	        
	    	      
	    	        
	    	        
	    	        for (String str : stringArray)
	    	        {
	    	           // System.out.println(str);
	    	        	obj.put("frequency", Collections.frequency(wordList, str));
	    	        	
	    	        	
	    	       
	    	        	
	    	        	
	    	        	
	    	        	
	    	        	
	    	        	ArrayList<String> word = new ArrayList<String>();
	    	        	FileInputStream fstream = new FileInputStream("/Users/rohanpatel/Documents/workspace1/index/"+stw);
	    	        	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	    	        	String strLine;

	    	        	//Read File Line By Line
	    	        	while ((strLine = br.readLine()) != null)   {
	    	        	  // Print the content on the console
	    	        		word.add(strLine);
	    	        	//  System.out.println (strLine);
	    	        	}

	    	        	//Close the input stream
	    	        	br.close();
                         
	    	        	if(!word.contains(str))
	    	        	{
	    	            obj.put("word", str);
	    	        	}
	    	        
	    	        File f2 = new File(index);
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

	    	}
	    	catch(FileNotFoundException e){
				
			}
			catch(Exception e){
				
			}
	    }
		}
		finally {
			new Rank().rank("Apple", rank);
			in.close();
		}
}
}
