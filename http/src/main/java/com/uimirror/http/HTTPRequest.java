package com.uimirror.http;

import static com.uimirror.common.CommonConstants.NEW_LINE;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * <p>
 * This class will request for the url and recieve the response as String(JSON)
 * or XML
 * 
 * @author Jayaram
 * @version 0.1
 * 
 */
public class HTTPRequest {
    
    private static final int _DEFAULT_TIME_OUT = 2000;
    
    /**
     * <p>This will open connection with default time out.
     * 
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private static InputStream getInputStream(String url) throws MalformedURLException, IOException {
	URLConnection gb = (new URL(url)).openConnection();
	gb.setReadTimeout(_DEFAULT_TIME_OUT);
	return gb.getInputStream();
    }
    
    
    /**
     * <p>This will open connection with time out ption.
     * @param url
     * @param timeOut
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private static InputStream getInputStream(String url, int timeOut) throws MalformedURLException, IOException {
	URLConnection gb = (new URL(url)).openConnection();
	gb.setReadTimeout(timeOut);
	return gb.getInputStream();
    }

    /**
     * <p>This will get the stream without timeout options.
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static StreamReader getStreamReader(String url) throws MalformedURLException, IOException {
	return new StreamReader(getInputStream(url));
    }
    
    /**
     * <>This will get the stream with timeout options.
     * @param url
     * @param timeOut
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static StreamReader getStreamReader(String url, int timeOut) throws MalformedURLException, IOException {
	return new StreamReader(getInputStream(url, timeOut));
    }

    /**
     * <p>This will retrieve JSON response from the url specified.
     * 
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getString(String url) throws MalformedURLException,
	    IOException {
	StringBuffer sb = new StringBuffer();
	StreamReader in = HTTPRequest.getStreamReader(url);
	if (in.hasNextLine()) {
	    sb.append(in.nextLine());
	    while (in.hasNextLine()) {
		sb.append(NEW_LINE);
		sb.append(in.nextLine());
	    }
	}
	in.close();
	return sb.toString();
    }
    
    /**
     * <p>This will retrieve JSON response from the url specified.
     * 
     * @param url
     * @param timeOut
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getString(String url, int timeOut) throws MalformedURLException, IOException {
	StringBuffer sb = new StringBuffer();
	StreamReader in = HTTPRequest.getStreamReader(url);
	if (in.hasNextLine()) {
	    sb.append(in.nextLine());
	    while (in.hasNextLine()) {
		sb.append(NEW_LINE);
		sb.append(in.nextLine());
	    }
	}
	in.close();
	return sb.toString();
    }

    /**
     * <p>This will read the XML Document.
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Document getXMLDoc(String url) throws MalformedURLException,
	    IOException, ParserConfigurationException, SAXException {
	InputStream ip = HTTPRequest.getInputStream(url);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(ip);
	doc.getDocumentElement().normalize();

	return doc;
    }

}
