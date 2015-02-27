package edu.calstatela.cs454.instructor.crawler;

import java.io.File;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws Exception {
		File f = new File("file1.json");
        
	       
	       if(f.exists())
	       {
	    	   f.delete();
	       }
		String v = args[0];
		//String d = args[1];
	
		new Crawl().crawl(v);
		
	
}
}
