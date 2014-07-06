package com.uimirror.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupRequest {
    
    private final String URL;
    private static final String USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0";
    private static final String HEADER_LANGUAGE="Accept-Language";
    private static final String HEADER_EN = "en";
    private static final String HEADER_ENCODING="Accept-Encoding";
    private static final String HEADER_GZIP="gzip,deflate,sdch";

    private JsoupRequest(String url) {
	this.URL = url;
    }
    
    public static JsoupRequest getInstance(String url){
	return new JsoupRequest(url);
    }
    
    /**
     * <p>this will get the document using header.
     * @return
     * @throws IOException
     */
    public Document getDocumentWithHeader() throws IOException{
	return Jsoup.connect(this.URL).userAgent(USER_AGENT).header(HEADER_LANGUAGE, HEADER_EN).header(HEADER_ENCODING, HEADER_GZIP).timeout(2).get();
    }
    
    /**
     * <p>This will gett he document without header.
     * @return
     * @throws IOException
     */
    public Document getDocument() throws IOException{
	return Jsoup.connect(this.URL).userAgent(USER_AGENT).get();
    }
    
    /**
     * <p>This will get the element using tag with header in request.
     * @param tag
     * @return
     * @throws IOException
     */
    public Elements getElementsByTagUsingHeader(String tag) throws IOException{
	return getDocumentWithHeader().getElementsByTag(tag);
    }
    
    /**
     * <p>This will get the element using tag.
     * @param tag
     * @return
     * @throws IOException
     */
    public Elements getElementsByTag(String tag) throws IOException{
	return getDocument().getElementsByTag(tag);
    }
    

}
