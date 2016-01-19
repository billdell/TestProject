package ots.com.test.ydata.pages;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.net.URL;


import com.thoughtworks.xstream.XStream;


public class ReadWebElementsFromXml {
	
	static public List<WebElementFromXml>   readWebElementsFromXmlAsList(String xmlFile) throws Exception{
		
		Class myClass = ReadWebElementsFromXml.class;
		ClassLoader loader = myClass.getClassLoader();
	//	Tell the class loader to find the file you want (it will search every directory on Java's class path).
		URL myURL = loader.getResource(xmlFile);
		String path = myURL.getPath();
		
		System.out.println("readWebElementsFromXmlAsList path - > " + path ) 	;
		FileReader reader = new FileReader(path); 
		
		
		InputStream inputStream = new FileInputStream(path );
		
		/* BufferedReader  in = new BufferedReader(new InputStreamReader(new FileInputStream("path"),"UTF8"));
		 String str = in.readLine();
		 System.out.println(str);*/
		
		InputStreamReader	inputStreamReader = new InputStreamReader(new FileInputStream(path),"UTF8"); 
		 BufferedReader  in = new BufferedReader(inputStreamReader ) ; 
		 
		 LinkedList<String> lines = new LinkedList();
		 String readLine;
		 String xml = null; 

		 while ((readLine = in.readLine()) != null) {
		     lines.add(readLine);
		     if (xml == null ) 
		    	 xml  = readLine; 
		     else
		    	 xml += readLine; 
		     
		    
		 }
		 
		 
		 
		String str = in.readLine(); 
		System.out.println(xml); 
		
		
		XStream xstream = new XStream();
	  	xstream.processAnnotations(PageWebElementsFromXml.class);
	  	xstream.processAnnotations(WebElementFromXml.class);
	  	xstream.processAnnotations(WebElementDetailsFromXml.class);
	  	
	  
	  	
	 
	  	List<WebElementFromXml> webElementsFromXmlList = new ArrayList<WebElementFromXml>(); 
	  	//PageWebElementsFromXml pageWebElementsFromXml = (PageWebElementsFromXml) xstream.fromXML(reader);
	  PageWebElementsFromXml pageWebElementsFromXml = (PageWebElementsFromXml) xstream.fromXML(inputStream);
	   //PageWebElementsFromXml pageWebElementsFromXml = (PageWebElementsFromXml) xstream.fromXML(xml);
	  	
	  	
	  	
	  	
	  
	  	Iterator<WebElementFromXml> iter = pageWebElementsFromXml.webElements.iterator();
	  	while(iter.hasNext()) {
	  	
	  		WebElementFromXml webElementFromXml = iter.next();
	  		webElementsFromXmlList.add(webElementFromXml) ;
	  			
	  	}
	  	return webElementsFromXmlList;
		
	}

	
	//static public Map<String,WebElementFromXml>   readWebElementsFromXmlasMap(String xmlFile) throws FileNotFoundException{
	static public PageWebElementsFromXml   readWebElementsFromXmlasMap(String xmlFile) throws Exception{
		
		
		
		Class myClass = ReadWebElementsFromXml.class;
		ClassLoader loader = myClass.getClassLoader();
		System.out.println( "loader - > " + loader ) ; 
		
		
		URL myURL = loader.getResource(xmlFile);
		String path = myURL.getPath();
		
		if ( path == null )  	System.out.println("readWebElementsFromXmlasMap path  is null " ) 	;
		System.out.println("readWebElementsFromXmlasMap path - > " + path ) 	;
		
		
		InputStream inputStream = new FileInputStream(path );
		
		
		InputStreamReader	inputStreamReader = new InputStreamReader(new FileInputStream(path),"UTF8"); 
		 BufferedReader  in = new BufferedReader(inputStreamReader ) ; 
		 in.toString(); 
		//String str = in.readLine(); 
		
		
		 LinkedList<String> lines = new LinkedList();
		 String readLine;
		 String xml = null; 

		 while ((readLine = in.readLine()) != null) {
		     lines.add(readLine);
		     if (xml == null ) 
		    	 xml  = readLine; 
		     else
		    	 xml += readLine; 
		 }
		 
		 
		 
		
		System.out.println(xml); 
		
		
		
		FileReader reader = new FileReader(path); 
		
		
		//Map<String,WebElementFromXml> webElementsFromXml = new HashMap<String,WebElementFromXml >();
		
		/* BufferedReader  in = new BufferedReader(new InputStreamReader(new FileInputStream("path"),"UTF8"));
		 String str = in.readLine();*/
		 
	  	XStream xstream = new XStream();
	  	xstream.processAnnotations(PageWebElementsFromXml.class);
	  	xstream.processAnnotations(WebElementFromXml.class);
	  	xstream.processAnnotations(WebElementDetailsFromXml.class);
	 
	  	
	  //	PageWebElementsFromXml pageWebElementsFromXml = (PageWebElementsFromXml) xstream.fromXML(reader);
	  //	PageWebElementsFromXml pageWebElementsFromXml = (PageWebElementsFromXml) xstream.fromXML(xml);
		PageWebElementsFromXml pageWebElementsFromXml = (PageWebElementsFromXml) xstream.fromXML(inputStream);

		
	  	pageWebElementsFromXml.initializeMaps();
	  	return pageWebElementsFromXml; 
	  	
	  	
	  
	  	
	  	
	  
		
	}
	
	
	/*static public ModalPagesFromXml   readModalsFromXml(String xmlFile) throws Exception{
		
		Class myClass = ReadWebElementsFromXml.class;
		ClassLoader loader = myClass.getClassLoader();
		System.out.println( "loader - > " + loader ) ; 
		
		
		URL myURL = loader.getResource(xmlFile);
		String path = myURL.getPath();
		
		InputStream inputStream = new FileInputStream(path );
		
		InputStreamReader	inputStreamReader = new InputStreamReader(new FileInputStream(path),"UTF8"); 
		BufferedReader  in = new BufferedReader(inputStreamReader ) ; 
		in.toString(); 
		//String str = in.readLine(); 
		
		
		LinkedList<String> lines = new LinkedList();
		String readLine;
		String xml = null; 

		while ((readLine = in.readLine()) != null) {
			lines.add(readLine);
		    if (xml == null ) 
		    	xml  = readLine; 
		    else
		    	xml += readLine; 
		}
		 
		System.out.println(xml); 
		//FileReader reader = new FileReader(path); 
		 
		XStream xstream = new XStream();
		xstream.processAnnotations(ModalPageWebElementsFromXml.class);
		xstream.processAnnotations(WebElementFromXml.class);
		xstream.processAnnotations(ModalPagesFromXml.class);
		xstream.processAnnotations(Grid.class);
		xstream.processAnnotations(Column.class);
		
		ModalPagesFromXml modalPagesFromXml = (ModalPagesFromXml) xstream.fromXML(inputStream);
		modalPagesFromXml.initialize();
		return modalPagesFromXml; 
		//return modalPagesFromXml.getModalsMap(); 
		
		//ModalPageWebElementsFromXml modalPageWebElementsFromXml = (ModalPageWebElementsFromXml) xstream.fromXML(inputStream);

			
	//	return modalPageWebElementsFromXml; 
	}*/
	
}
