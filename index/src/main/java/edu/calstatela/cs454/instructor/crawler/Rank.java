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

public class Rank {
int i = 1;
	@SuppressWarnings("unchecked")
	public void rank(String word, String rank) throws IOException{
		
		
		final InputStream in = new FileInputStream("/Users/Bhavik/Documents/rohan/index/index.json");
		
		Map<String, Integer> words = new HashMap<String, Integer>();
		Integer tr;
		
		
		try {
			for (Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> keyValue = (LinkedHashMap<String,Object>) it.next();
			    String a = (String) keyValue.get("word");
				
				if(word.toLowerCase().equals(a.toLowerCase())){
		//	System.out.println(keyValue.get("frequency"));
			//	System.out.println(keyValue.get("word"));
			//	System.out.println(keyValue.get("link"));
				String local = (String) keyValue.get("link");
				Integer frq = (Integer) keyValue.get("frequency");
				
			
				
			//	float r2 = Float.parseFloat(frq)/100;
					
					String t = (String) keyValue.get("title");
					//System.out.println(t);
					String[] tarray = t.split("\\s+");
					for (int i=0; i<tarray.length; ++i)
				    {
				        tarray[i] = tarray[i].toLowerCase();
				    }
					List<String> tList = Arrays.asList(tarray);
					
					
						
						    tr = Collections.frequency(tList, word.toLowerCase());
						   // System.out.println(tr);
						    Integer v = tr*100;
						     Integer fr = frq + v;
						     words.put(local, fr);
						   // System.out.println(fr);
							
							
							
						
						
						
				}
				
				
			}
			/*for(String w:words.keySet()){
				System.out.println("key:::"+w.toString());
				System.out.println("value:::"+words.get(w).toString());
			}*/
			
			Object[] a = words.entrySet().toArray();
		    Arrays.sort(a, new Comparator() {
		        public int compare(Object o1, Object o2) {
		            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
		                    ((Map.Entry<String, Integer>) o1).getValue());
		        }
		    });
		    for (Object e : a) {
		       // System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
		             //   + ((Map.Entry<String, Integer>) e).getValue());
		        
		    	String r = ((Entry<String, Integer>) e).getValue().toString();
		    	float r1 = Float.parseFloat(r)/100;
		        JSONObject obj = new JSONObject();
		        
		        obj.put("link",((Map.Entry<String, Integer>) e).getKey());
		       // obj.put("frequency", ((Map.Entry<String, Integer>) e).getValue());
		        obj.put("rank",r1);
		        i++;
		        
		        File f4 = new File(rank);
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


