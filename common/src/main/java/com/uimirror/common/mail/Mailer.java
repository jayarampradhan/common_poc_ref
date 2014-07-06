/**
 * 
 */
package com.uimirror.common.mail;

import java.io.Serializable;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.uimirror.common.exception.CommonErrorConstants;
import com.uimirror.common.exception.CommonSystemException;


/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This class will help to organize the mailing functionality.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 29-Mar-2014 8:35:27 PM
 * @modifiedby Jayaram
 * @modifiedon 29-Mar-2014 8:35:27 PM
 * ***********************************************************************
 */
public final class Mailer implements Serializable{
	
    private static final long serialVersionUID = 3132190246171913931L;
    protected static Logger LOG = LoggerFactory.getLogger(Mailer.class);

    private JavaMailSender mailSender;

	public void sendMail(final MimeMessage message) throws CommonSystemException{
		
	    LOG.info("[START]- Sending Email.");
	    
	    try {
	    	mailSender.send(message);
	    	LOG.info("[INTERIM]- A mail has been sent.");
	    } catch (MailException e) {
	    	LOG.warn("Error occured while sending the mail, the excption for the same is: ()", e);
	    	throw new CommonSystemException("Can't Send mail Now", CommonErrorConstants._MAIL_SEND_ERR_CD, e);
	    }finally{
	    	LOG.info("[END]- Sending Email.");
	    }
	}

	public void setMailSender(JavaMailSender mailSender) {
	    this.mailSender = mailSender;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

}
