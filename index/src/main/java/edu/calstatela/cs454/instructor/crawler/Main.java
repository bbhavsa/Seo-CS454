package edu.calstatela.cs454.instructor.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import netscape.ldap.util.GetOpt;

import org.apache.commons.lang.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
//import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	
	public static void main(String[] args) throws IOException, SAXException, TikaException {
		
 
		 
		GetOpt options = new GetOpt( "c:s:i:r:H", args );
	       //Get the arguments specified for each option.
		 String controlfile = options.getOptionParam('c');
		   String stw = options.getOptionParam('s');
	     String index = options.getOptionParam( 'i' );
	     String rank = options.getOptionParam( 'r' );
	   
	     
File f2 = new File(index);
		 
		// String controlfile = args[0];
		 if(f2.exists())
		 {
			 f2.delete();
		 }
	      
File f3 = new File(rank);

if(f3.exists()){
	f3.delete();
}
		// new Rank().rank("Mac");
		
	new Index().index(controlfile, stw, index, rank);
	}
}
