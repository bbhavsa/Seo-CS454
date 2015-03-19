package edu.calstatela.cs454.instructor.crawler;

import java.awt.Event;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import gnu.getopt.Getopt;
import netscape.ldap.*;
import netscape.ldap.controls.*;
import netscape.ldap.util.*;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Main {

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, SAXException, TikaException {
		
		
		
		
		 File f = new File("file2.json");
		 
			// String c = args[0];
			 if(f.exists())
			 {
				 f.delete();
			 }
			 
			 GetOpt options = new GetOpt( "c:H", args );
		       //Get the arguments specified for each option.
		      String controlfile = options.getOptionParam( 'c' );
		       new Extraction().extract(controlfile);
		      
		      
		    
		//InputStream in = new FileInputStream("D:/crawler3-app/target/"+controlfile);
		
}
}
	
	

	
	


 