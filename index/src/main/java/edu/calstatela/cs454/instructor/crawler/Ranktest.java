package edu.calstatela.cs454.instructor.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

public class Ranktest {
	int i = 1;
	@SuppressWarnings("unchecked")
	public void rank2(String word) throws IOException{
		
		final InputStream in = new FileInputStream("/Users/Bhavik/Documents/rohan/index/rank.json");
		
		Map<String, Double> words = new HashMap<String, Double>();
		
		
		try {
			for (Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {
				// System.out.println(it.next());
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> keyValue = (LinkedHashMap<String,Object>) it.next();
			//	LinkedHashMap<String, Integer> fre = (LinkedHashMap<String,Integer>) it.next();
				
				if(word.equals(keyValue.get("word"))){
		
				String local = (String) keyValue.get("Link");
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
		    for (Object e : a) {
		       // System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
		             //   + ((Map.Entry<String, Integer>) e).getValue());
		        
		    	String r = ((Entry<String, Double>) e).getValue().toString();
		    	//float r1 = Float.parseFloat(r)/100;
		        JSONObject obj = new JSONObject();
		        
		        obj.put("link",((Map.Entry<String, Double>) e).getKey());
		        obj.put("frequency", ((Map.Entry<String, Double>) e).getValue());
		        obj.put("rank",r);
		        i++;
		        
		        File f4 = new File("rank1.json");
		          BufferedWriter file2 = new BufferedWriter(new FileWriter(f4,true)); 
		            try {
		            	
		            	ObjectMapper mapper = new ObjectMapper();
		               file2.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
		            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
		                file2.newLine();
		                file2.newLine();
		                file2.newLine();
		                file2.newLine();
		               
		     
		            } catch (IOException e1) {
		                e1.printStackTrace();
		     
		            } finally {
		                file2.flush();
		                file2.close();
		            }
		    }
			
			
			
	
	}
	finally {
		in.close();
	}
		
	}
	
	
	
}
