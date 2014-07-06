/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common
 * @createdOn 08-Mar-20142:59:12 am
 * @modifiedby Jayaram
 * @modifiedon 08-Mar-20142:59:12 am
 * ***********************************************************************
 */

package com.uimirror.api.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;

public class ExcelToDrl {
    static final String XLS_NAME = "Modify_Commands_Rules.xls";
    static final String DESTINATION = "/Users/Jayaram/Uimirror_J_ws/ws-common/src/test/resource";
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
	// create an input stream
	InputStream is = null;
	
	try {
	    // assign the excel to the input stream
	    // mention the local directory path where your excel is kept
	    // you can take any decision table (excel sheet) for testing
	    is = ExcelToDrl.class.getClassLoader().getResourceAsStream(XLS_NAME);
	    if(is == null){
		throw new IllegalArgumentException("Resource Not Found");
	    }
	} catch (Exception e) {
	    System.out.println(e);
	}
	// create compiler class instance
	SpreadsheetCompiler sc = new SpreadsheetCompiler();
	// compile the excel to generate the (.drl) file
	String drl = sc.compile(is, InputType.XLS);
	String fileName = XLS_NAME.split("\\.")[0];
	PrintWriter writer = new PrintWriter(DESTINATION+"/"+fileName+".drl", "UTF-8");
	writer.println(drl);
	writer.close();
	// check the generated (.drl) file
	System.out.println("Generate DRL file is –: ");
	System.out.println(drl);
    }
}
