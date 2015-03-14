package edu.calstatela.cs454.instructor.crawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class Rank {

	public void rank(String word) throws IOException{
		
		final InputStream in = new FileInputStream("/Users/rohanpatel/Documents/workspace1/index/target/index.json");
		
		try {
			for (Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {
				// System.out.println(it.next());
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, String> keyValue = (LinkedHashMap<String,String>) it.next();
				System.out.println(keyValue.get("frequency"));
				System.out.println(keyValue.get("word"));
				
				
			}
		
		
	
	}
	finally {
		in.close();
	}
		
	}
	
}
