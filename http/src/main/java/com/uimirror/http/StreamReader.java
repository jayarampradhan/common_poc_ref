package com.uimirror.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>
 * This will read the http stream.
 * 
 * @author Jayaram
 * @version 0.1
 * 
 */
public class StreamReader {

    private String line = null;
    private BufferedReader buffRead = null;
    private InputStream inpStr = null;

    public StreamReader(InputStream inputStream) throws IOException {
	inpStr = inputStream;
	buffRead = new BufferedReader(new InputStreamReader(inputStream));
	line = buffRead.readLine();
    }

    public boolean hasNextLine() {
	return line != null;
    }

    /**
     * <p>This reads the next line from the response.
     * @return
     * @throws IOException
     */
    public String nextLine() throws IOException {
	String ret = line;
	if (line != null) {
	    line = buffRead.readLine();
	}
	return ret;
    }

    /**
     * <p>This will be the destroy method to close any open resource.
     * 
     * @throws IOException
     */
    public void close() throws IOException {
	inpStr.close();
	buffRead.close();
    }

}
