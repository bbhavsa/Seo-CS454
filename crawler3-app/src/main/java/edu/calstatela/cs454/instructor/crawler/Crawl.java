package edu.calstatela.cs454.instructor.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
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
	  int i = 1;
	 
    @SuppressWarnings("unchecked")
    public void crawl(String Url) throws Exception{
    	TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                }
        };
        URL url = new URL(Url);
        
       
        URLConnection hpCon = url.openConnection();
        
        InputStream input = url.openStream();
        try{
			SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		catch(Exception e){
			System.out.println(e);
		}
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("E:/Storage/information"+i+".html");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        String path = "E:/Storage/information"+i+".html";
        i++;
        
        
        
        LinkContentHandler linkHandler = new LinkContentHandler();
        ContentHandler textHandler = new BodyContentHandler();
        ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
        TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        AutoDetectParser parser = new AutoDetectParser();
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
        obj.put("Local storage", path);
        
     // System.out.println(links);
        for (Link link: links)
        {
        	String anchor = link.getUri();
        	/*if(anchor.endsWith(".jpg"))
        	{
        		File destination = new File("f1.pdf");
        		FileUtils.copyURLToFile(url, destination);
        	}*/
        
        	
        	
        	if(h<2)
        	{
        		if(!link.isImage())
        		{
              myQueue.add(anchor);
        		}
        		}
        	
            String name = link.getText();
        	company.add("text:" +name);
        	company.add("Url:" +anchor);
        	
        	obj2.put("Anchors", company);
        	obj.put("Links", obj2);
        }
        h++;
  
        for(String v: myQueue)
        {
        	if(!v.startsWith("#") && !v.startsWith("/")||v.startsWith("http")||v.startsWith("https"))
        		try{
        	crawl(v);
        	}
        	catch( MalformedURLException malformedException){
        		
        	}
        	catch (FileNotFoundException e){
        	    // do stuff here..
        	   }
        	catch(IOException ex){
        }
        }
      
        
      File f = new File("file1.json");
      BufferedWriter file = new BufferedWriter(new FileWriter(f,true)); 
        try {
            file.write(obj.toJSONString());
            file.newLine();
            file.newLine();
            file.newLine();
            file.newLine();
           // System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: \n "  + obj );
            String newLine = System.getProperty("line.separator");
            System.out.println(newLine);
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
        
  
    }
  
}