package edu.calstatela.cs454.instructor.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.ContentHandler;

public class Crawl {
	/*public void start() throws Exception{
		String v = args[0];
		crawl(v);
		}*/
	  int h = 1;
	  public String getdepth(String depth)
		{
		   return depth;
			
			
		}
    @SuppressWarnings("unchecked")
    public void crawl(String Url) throws Exception,MalformedURLException{
        URL url = new URL(Url);
       
        URLConnection hpCon = url.openConnection();
        
        InputStream input = url.openStream();
        LinkContentHandler linkHandler = new LinkContentHandler();
        ContentHandler textHandler = new BodyContentHandler();
        ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
        TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        HtmlParser parser = new HtmlParser();
        parser.parse(input, teeHandler, metadata, parseContext);
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONArray  company = new JSONArray();
        
        List<Link> links = linkHandler.getLinks();
       // ArrayList<String> linklist = new ArrayList<String>();
        Queue<String> myQueue = new LinkedList<String>();
        obj.put("Title", metadata.get("title"));
        obj.put("Content-Type",hpCon.getContentType());
        obj.put("URL", url);
        obj.put("Last pull date",new Date(hpCon.getLastModified()));
      
        for (Link link: links)
        {
        	String anchor = link.getUri();
        	
        	if(h<2)
        	{
        		if(!link.isImage())
        		{
              myQueue.add(anchor);
        		}
        		}
        	/*if(!link.isImage())
        	{
            crawl(anchor);
        	}*/
        	String name = link.getText();
        	company.add("text:" +name);
        	company.add("Url:" +anchor);
        	
        	obj2.put("Anchors", company);
        	obj.put("Links", obj2);
        }
        h++;
   //  System.out.println(myQueue);
        for(String v: myQueue)
        {
        	crawl(v);
        }
        
        FileWriter file = new FileWriter("file1.txt");
        try {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: \n "  + obj );
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
        
  
    }
  
}