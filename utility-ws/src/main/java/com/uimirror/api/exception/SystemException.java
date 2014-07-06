package com.uimirror.api.exception;

import com.uimirror.common.util.StringUtility;

public class SystemException extends Exception {

    private static final long serialVersionUID = 7465356062876804391L;

    private int errCode;// This is the customizing error code to be shown to the
			// user
    private Exception exception;// This is actual Exception occurred

    /**
     * <p>
     * Default Constructor to initialize the custom exception with out error
     * code and error message
     */
    public SystemException() {
	super();
	this.exception = null;
    }

    /**
     * <p>
     * This will only initialize the error message
     * 
     * @param errMsg
     *            custom error message
     */
    public SystemException(String errMsg) {
	super(errMsg);
	this.exception = null;
    }

    /**
     * <p>
     * This is the error code for the customizing the error message and error
     * code
     * 
     * @param errMsg
     *            customize error message
     * @param errCode
     *            customize error Code
     */
    public SystemException(String errMsg, int errCode) {
	super(errMsg);
	this.errCode = errCode;
	this.exception = null;
    }

    /**
     * <p>
     * This constructor will initialize the customize error code
     * 
     * @param errCode
     *            custom error code
     */
    public SystemException(int errCode) {
	this.errCode = errCode;
	this.exception = null;
    }

    /**
     * <p>
     * This constructor will initialize the custom error message and actual
     * exception occurred
     * 
     * @param errMsg
     *            custom error message
     * @param paramException
     *            actual exception that occurred
     */
    public SystemException(String errMsg, Exception paramException) {
	super(errMsg);
	this.exception = paramException;
    }

    /**
     * <p>
     * This will initialize the actual exception
     * 
     * @param paramException
     */
    public SystemException(Exception paramException) {
	this.exception = paramException;
    }

    /**
     * <p>
     * This will initialize the custom message, custom error code and actual
     * exception
     * 
     * @param errMsg
     *            custom error message
     * @param errCode
     *            custom error code
     * @param paramException
     *            actual exception
     */
    public SystemException(String errMsg, int errCode,
	    Exception paramException) {
	super(errMsg);
	this.errCode = errCode;
	this.exception = paramException;
    }

    @Override
    public String getMessage() {

	String str = super.getMessage();

	if (StringUtility.checkEmptyString(str) && (this.exception != null)) {
	    return this.exception.getMessage();
	}
	return str;
    }

    @Override
    public Throwable getCause() {
	return this.exception;
    }

    @Override
    public String toString() {

	if (this.exception != null) {
	    return this.exception.toString();
	}
	return super.toString();
    }

    public Exception getException() {
	return this.exception;
    }

    public int getErrCode() {
	return errCode;
    }

}
