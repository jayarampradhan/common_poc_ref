/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.mail
 * @createdOn 29-Mar-2014 8:58:25 PM
 * @modifiedby Jayaram
 * @modifiedon 29-Mar-2014 8:58:25 PM
 * ***********************************************************************
 */
package com.uimirror.common.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Helper class which will be used to build 
 * the mail message.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 29-Mar-2014 8:58:25 PM
 * @modifiedby Jayaram
 * @modifiedon 29-Mar-2014 8:58:25 PM
 * ***********************************************************************
 */
public class Email {
	private final JavaMailSender mailSender;
	private final MimeMessage message;
	private final MimeMessageHelper helper;
	//Make constructor as private for thread safety
	/**
	 * <p>Creates the mime message in combination with mimemessage and a helper
	 * @param mailSender
	 * @param multiPart
	 * @throws MessagingException
	 */
	private Email(final JavaMailSender mailSender, boolean multiPart) throws MessagingException{
		this.mailSender = mailSender;
		this.message = this.mailSender.createMimeMessage();
		this.helper = new MimeMessageHelper(message, multiPart);
	}
	
	/**
	 * <p>Add To whom you want to send email.
	 * @param to
	 * @return
	 * @throws MessagingException
	 */
	public Email setTo(InternetAddress[] to) throws MessagingException{
		this.helper.setTo(to);
		return this;
	}
	
	/**
	 * <p>Add to whom you want to send email.
	 * @param to
	 * @return
	 * @throws MessagingException
	 */
	public Email setTo(InternetAddress to) throws MessagingException{
		this.helper.setTo(to);
		return this;
	}
	
	/**
	 * <p>Add to whom you want to send email.
	 * @param to
	 * @return
	 * @throws MessagingException
	 */
	public Email setTo(String to) throws MessagingException{
		this.helper.setTo(to);
		return this;
	}
	
	/**
	 * <p>Add to whom you want to send email.
	 * @param to
	 * @return
	 * @throws MessagingException
	 */
	public Email setTo(String ... to) throws MessagingException{
		this.helper.setTo(to);
		return this;
	}
	
	/**
	 * <p>Add Subject Line for the email.
	 * @param subject
	 * @return
	 * @throws MessagingException
	 */
	public Email setSubject(final String subject) throws MessagingException{
		this.helper.setSubject(subject);
		return this;
	}
	
	/**
	 * <p>Set from source of email generating
	 * @param fromMail
	 * @param alias
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public Email setFrom(final String fromMail, final String alias) throws UnsupportedEncodingException, MessagingException{
		this.helper.setFrom(fromMail, alias);
		return this;
	}
	
	/**
	 * <p>Set from source of email generating
	 * @param from
	 * @return
	 * @throws MessagingException
	 */
	public Email setForm(final InternetAddress from) throws MessagingException{
		this.helper.setFrom(from);
		return this;
	}
	
	/**
	 * <p>Set from source of email generating
	 * @param from
	 * @return
	 * @throws MessagingException
	 */
	public Email setFrom(final String from) throws MessagingException{
		this.helper.setFrom(from);
		return this;
	}

	/**
	 * <p>Set priority of the message 1 for highest
	 * @param pripority
	 * @return
	 * @throws MessagingException
	 */
	public Email setPriority(int pripority) throws MessagingException{
		this.helper.setPriority(pripority);
		return this;
	}
	
	/**
	 * <p>Set true, if you want to validate the sender email id before sending
	 * @param validation
	 * @return
	 */
	public Email setValidateAddresses(boolean validation){
		this.helper.setValidateAddresses(validation);
		return this;
	}
	
	/**
	 * <p>Sets the text or body of the email
	 * @param text
	 * @param html
	 * @return
	 * @throws MessagingException
	 */
	public Email setText(final String text, final String html) throws MessagingException{
		this.helper.setText(text, html);
		return this;
	}
	
	/**
	 * <p>Sets the text or body of the email
	 * @param text
	 * @return
	 * @throws MessagingException
	 */
	public Email setText(final String text) throws MessagingException{
		this.helper.setText(text);
		return this;
	}
	
	/**
	 * <p>Sets In line attachment if any to the body
	 * <p>Please note it should be always after setText operation.
	 * @param resource
	 * @param contentId
	 * @return
	 * @throws MessagingException
	 */
	public Email addInline(Resource resource, String contentId) throws MessagingException{
		this.helper.addInline(contentId, resource);
		return this;
	}
	
	/**
	 * <p>Sets In line attachment if any to the body
	 * <p>Please note it should be always after setText operation.
	 * @param source
	 * @param contentId
	 * @param contentType
	 * @return
	 * @throws MessagingException
	 */
	public Email addInline(InputStreamSource source, String contentId, String contentType) throws MessagingException{
		this.helper.addInline(contentId, source, contentType);
		return this;
	}
	
	/**
	 * <p>Finally get the message you want to send.
	 * @return
	 */
	public MimeMessage getMessage(){
		return this.message;
	}
	
	/**
	 * <p>Gets the thread safe instance
	 * @param mailSender
	 * @return
	 * @throws MessagingException
	 */
	public static Email getInstance(final JavaMailSender mailSender) throws MessagingException{
		return new Email(mailSender, Boolean.TRUE);
	}
	
	/**
	 * <p>Gets the thread safe instance
	 * @param mailSender
	 * @param multiPart
	 * @return
	 * @throws MessagingException
	 */
	public static Email getInstance(final JavaMailSender mailSender, final boolean multiPart) throws MessagingException{
		return new Email(mailSender, multiPart);
	}

}
