package edu.calstatela.cs454.instructor.crawler;

import java.io.File;
import java.io.IOException;

import netscape.ldap.util.GetOpt;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringStyle;


public class Main {

	public static void main(String[] args) throws Exception {
		FileUtils.deleteDirectory(new File("E:/Storage/"));
		File f = new File("file1.json");
		
		
	       
	       if(f.exists())
	       {
	    	   f.delete();
	       }
	       
	//	String v = args[0];
	  //  String d = args[1];
	       GetOpt options = new GetOpt( "h:D:H", args );
	       //Get the arguments specified for each option.
	       String hostname = options.getOptionParam( 'h' );
	      
	       String dep = options.getOptionParam( 'D' );
	
		new Crawl().crawl(hostname,dep);
		
	
}
}