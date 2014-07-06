/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.service
 * @createdOn 29-Mar-2014 10:09:10 PM
 * @modifiedby Jayaram
 * @modifiedon 29-Mar-2014 10:09:10 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.register.service;

import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.mail.Email;
import com.uimirror.common.mail.Mailer;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : this class will take care about sending 
 * email to newly registered user.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 29-Mar-2014 10:09:10 PM
 * @modifiedby Jayaram
 * @modifiedon 29-Mar-2014 10:09:10 PM
 * ***********************************************************************
 */
public class WelcomeMailCallable implements Callable<Object>{
	protected static final Logger LOG = LoggerFactory.getLogger(WelcomeMailCallable.class);
	private final Mailer mailer;
	private final String name;
	private final String email;
	private final String formVerifyToken;
	private final String link;
	private final String to;
	
	private static final String R_NAME = ":name:";
	private static final String R_EMAIL = ":email:";
	private static final String R_TOKEN = ":formVerifyToken:";
	private static final String R_LINK = ":verifylink:";
	private static final String S_HTML = "/register/welcome_mail.html";
	private static final String S_TXT = "/register/welcome_mail.txt";
	private static final String FROM = "No-Reply@uimirror.com";
	private static final String FROM_ALIAS = "UIMirror Team";
	private static final String SUBJECT = "Welcome to UIMirror - Verification";
	private static final String CONTENT_ID = "logo";
	private static final String LOGO = "logo.png";
	
	public WelcomeMailCallable(final Mailer mailer, final String name, final String email, final String formVerifyToken, final String link, final String to){
		this.mailer = mailer;
		this.name = name;
		this.email = email;
		this.formVerifyToken = formVerifyToken;
		this.link = link;
		this.to = to;
	}

	@Override
	public Object call() throws Exception {
		LOG.info("[START]- Sending Email for newly registered user.");
		//Load the HTML TEXT
		String htmlText = null;
		htmlText = IOUtils.toString(this.getClass().getResourceAsStream(S_HTML), CommonConstants.UTF_8);
		htmlText = htmlText.replace(R_NAME, name);
		htmlText = htmlText.replace(R_EMAIL, email);
		htmlText = htmlText.replace(R_TOKEN, formVerifyToken);
		htmlText = htmlText.replace(R_LINK, link);
		
		//Load the TEXT		
		String text = IOUtils.toString(this.getClass().getResourceAsStream(S_TXT), CommonConstants.UTF_8);
		text = text.replace(R_NAME, name);
		text = text.replace(R_EMAIL, email);
		text = text.replace(R_TOKEN, formVerifyToken);
		text = text.replace(R_LINK, link);
		
		ClassPathResource classPathResource = new ClassPathResource(LOGO);
		
		mailer.sendMail(Email.getInstance(mailer.getMailSender(), Boolean.TRUE)
				.setFrom(FROM, FROM_ALIAS).setTo(to).setSubject(SUBJECT)
				.setText(text, htmlText)
				.addInline(classPathResource, CONTENT_ID).setPriority(1)
				.getMessage());
		
		LOG.info("[END]- Sending Email for newly registered user.");
		return null;
	}

}
