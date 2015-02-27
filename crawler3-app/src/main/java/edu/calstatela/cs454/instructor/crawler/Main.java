package edu.calstatela.cs454.instructor.crawler;

import java.io.File;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws Exception {
		/*File v = new File("hw.txt");
    	if(v.exists())
    	{
    		v.delete();
    	}*/
		String v = "http://www.mit.edu/";
		String d = "2";
		new Crawl().getdepth(d);
		new Crawl().crawl(v);
		
	
}
}
