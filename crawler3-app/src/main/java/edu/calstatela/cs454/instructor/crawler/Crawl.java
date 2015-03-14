package edu.calstatela.cs454.instructor.crawler;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
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

import com.fasterxml.jackson.databind.ObjectMapper;

public class Crawl {
	/*public void start() throws Exception{
		String v = args[0];
		crawl(v);
		}*/
	  int h = 1;
	  int i = 1;
	 
    @SuppressWarnings("unchecked")
    public void crawl(String Url,String depth)throws Exception{
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
        File theDir = new File("Storage");
        if (!theDir.exists()) {
            
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
             } catch(SecurityException se){
                //handle it
             }
             
          }
        
       
        
        
        
        LinkContentHandler linkHandler = new LinkContentHandler();
        ContentHandler textHandler = new BodyContentHandler();
        ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
        TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        AutoDetectParser parser = new AutoDetectParser();
        parser.parse(input, teeHandler, metadata, parseContext);
        
       
       
        //String x = hpCon.getContentType();
         //if(x.contains("html"));
        
        
        
       
        
        JSONObject obj = new JSONObject();
        
        JSONArray  company = new JSONArray();
        JSONArray  company2 = new JSONArray();
        
        
        List<Link> links = linkHandler.getLinks();
        ArrayList<String> linklist = new ArrayList<String>();
        ArrayList<String> linklist2 = new ArrayList<String>();
        Queue<String> myQueue = new LinkedList<String>();
        obj.put("Title", metadata.get("title"));
        obj.put("URL", url);
        obj.put("Content-Type",hpCon.getContentType());
        obj.put("Last pull date",new Date(hpCon.getLastModified()));
        String dir = "D:/Storage";
        try{
        String w = hpCon.getContentType();
        String id = UUID.randomUUID().toString();
        	
        if(w.contains("html"))
        {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("Storage/"+id+".html");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        String path = "Storage/"+id+".html";
        linklist2.add(path);
        i++;
        obj.put("storage", path);
        }
        else if(w.contains("png"))
        {
        	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("Storage/"+id+".png");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            String path = "Storage/"+id+".png";
            linklist2.add(path);
            i++;
            obj.put("storage", path);
        }
        else if(w.contains("gif"))
        {
        	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("Storage/"+id+".gif");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            String path = "Storage/"+id+".gif";
            linklist2.add(path);
            i++;
            obj.put("storage", path);
        }
        else if(w.contains("jpeg")||w.contains("jpg"))
        {
        	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("Storage/"+id+".jpg");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            String path = "Storage/"+id+".jpg";
            linklist2.add(path);
            i++;
            obj.put("storage", path);
        }
        else if(w.contains("pdf"))
        {
        	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("Storage/"+id+".pdf");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            String path = "Storage/"+id+".pdf";
            linklist2.add(path);
            i++;
            obj.put("storage", path);
        }
        else if(w.contains("doc"))
        {
        	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("Storage/"+id+".doc");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            String path = "Storage/"+id+".doc";
            linklist2.add(path);
            i++;
            obj.put("storage", path);
        }
        else if(w.contains("audio"))
        {
        	//URLConnection conn = new URL("http://online1.tingclass.com/lesson/shi0529/43/32.mp3").openConnection();
            InputStream is = hpCon.getInputStream();

            OutputStream outstream = new FileOutputStream(new File("Storage/"+id+".mp3"));
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
            String path = "Storage/"+id+".mp3";
            linklist2.add(path);
            i++;
            obj.put("storage", path);
        }
        
       
       
        }
        catch(NullPointerException e)
        {
        	
        }
     // System.out.println(links);
        for (Link link: links)
        {
        	String anchor = link.getUri();
        	if(!anchor.startsWith("htt"))
        	{
        		anchor = url+anchor;
        	}
        	//String v = link.toString();
        /*	try{
        	 URL url2 = new URL(anchor);
             
             
             URLConnection hpCon2 = url2.openConnection();
        	
        	 
        	        String m = hpCon2.getContentType();
        	        	
        	        if(m.contains("html"))
        	        {
        	        ReadableByteChannel rbc = Channels.newChannel(url2.openStream());
        	        FileOutputStream fos = new FileOutputStream("D:/Storage/information"+i+".html");
        	        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	        String path = "D:/Storage/information"+i+".html";
        	        linklist2.add(path);
        	        i++;
        	        obj.put("storage", path);
        	        }
        	        else if(m.contains("png"))
        	        {
        	        	ReadableByteChannel rbc = Channels.newChannel(url2.openStream());
        	            FileOutputStream fos = new FileOutputStream("D:/Storage/information"+i+".png");
        	            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	            String path = "D:/Storage/information"+i+".png";
        	            linklist2.add(path);
        	            i++;
        	            obj.put("storage", path);
        	        }
        	        else if(m.contains("gif"))
        	        {
        	        	ReadableByteChannel rbc = Channels.newChannel(url2.openStream());
        	            FileOutputStream fos = new FileOutputStream("D:/Storage/information"+i+".gif");
        	            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	            String path = "D:/Storage/information"+i+".gif";
        	            linklist2.add(path);
        	            i++;
        	            obj.put("storage", path);
        	        }
        	        else if(m.contains("jpeg")||m.contains("jpg"))
        	        {
        	        	ReadableByteChannel rbc = Channels.newChannel(url2.openStream());
        	            FileOutputStream fos = new FileOutputStream("D:/Storage/information"+i+".jpg");
        	            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	            String path = "D:/Storage/information"+i+".jpg";
        	            linklist2.add(path);
        	            i++;
        	            obj.put("storage", path);
        	        }
        	        else if(m.contains("pdf"))
        	        {
        	        	ReadableByteChannel rbc = Channels.newChannel(url2.openStream());
        	            FileOutputStream fos = new FileOutputStream("D:/Storage/information"+i+".pdf");
        	            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	            String path = "D:/Storage/information"+i+".pdf";
        	            linklist2.add(path);
        	            i++;
        	            obj.put("storage", path);
        	        }
        	        else if(m.contains("doc"))
        	        {
        	        	ReadableByteChannel rbc = Channels.newChannel(url2.openStream());
        	            FileOutputStream fos = new FileOutputStream("D:/Storage/information"+i+".doc");
        	            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        	            String path = "D:/Storage/information"+i+".doc";
        	            linklist2.add(path);
        	            i++;
        	            obj.put("storage", path);
        	        }
        	        else if(m.contains("audio"))
        	        {
        	        	//URLConnection conn = new URL("http://online1.tingclass.com/lesson/shi0529/43/32.mp3").openConnection();
        	            InputStream is = hpCon2.getInputStream();

        	            OutputStream outstream = new FileOutputStream(new File("D:/Storage/information"+i+".mp3"));
        	            byte[] buffer = new byte[4096];
        	            int len;
        	            while ((len = is.read(buffer)) > 0) {
        	                outstream.write(buffer, 0, len);
        	            }
        	            outstream.close();
        	            String path = "D:/Storage/information"+i+".mp3";
        	            linklist2.add(path);
        	            i++;
        	            obj.put("storage", path);
        	        }
        	        
        	       
        	       
        	        }
        	        catch(NullPointerException e)
        	        {
        	        	
        	        }
        	 catch (MalformedURLException e)
        	 {
        		 
        	 }*/
        	JSONObject obj2 = new JSONObject();
        	
       
        
        	
        	int dep=Integer.parseInt(depth);
        	if(h<dep)
        	{
        	
        		
        			if(!myQueue.contains(anchor))
        			{
              myQueue.add(anchor);
        			}
        		
        		}
        	
            String name = link.getText();
        	
        	
        	obj2.put("text", name);
           
        	obj2.put("URL", anchor);
        	company.add(obj2);
        }
        obj.put("Links", company);
       // company2.
        h++;
  
        for(String v: myQueue)
        {
        	if(!v.startsWith("#") && !v.startsWith("/")||v.startsWith("http")||v.startsWith("https"))
        		try{
        		//	String d= "2";
        	crawl(v,depth);
        	}
        	catch( MalformedURLException malformedException){
        		
        	}
        	catch (FileNotFoundException e){
        	    // do stuff here..
        	   }
        	catch(IOException ex){
        }
        }
     
    //   System.out.println(linklist);
      
      File f = new File("file1.json");
      
      BufferedWriter file = new BufferedWriter(new FileWriter(f,true)); 
        try {
        	ObjectMapper mapper = new ObjectMapper();
           file.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
           System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        //	file.write(obj.toJSONString());
            file.newLine();
            file.newLine();
            file.newLine();
            file.newLine();
           // System.out.println("Successfully Copied JSON Object to File...");
            
       //     System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        //    System.out.println("\nJSON Object: \n "  + obj );
          //  String newLine = System.getProperty("line.separator");
           // System.out.println(newLine);
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
        
        
  
}
}