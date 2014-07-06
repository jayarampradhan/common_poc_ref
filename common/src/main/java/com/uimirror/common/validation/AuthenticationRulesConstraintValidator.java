/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.validation
 * @createdOn 12-Mar-20141:01:50 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:01:50 am
 * ***********************************************************************
 */

package com.uimirror.common.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoServerSelectionException;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.annotation.AuthenticationRuleConstraint;
import com.uimirror.common.authentication.service.AuthenticationCheckPoint;
import com.uimirror.common.bean.validation.Errors;
import com.uimirror.common.util.ValidationMessageBuilder;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : validates rules defined in the drl for token, 
 * email etc. 
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Mar-20141:01:50 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:01:50 am
 * ***********************************************************************
 */

public class AuthenticationRulesConstraintValidator implements ConstraintValidator<AuthenticationRuleConstraint, Object>{

    protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationRulesConstraintValidator.class);
    
    @Autowired
    private StatelessKnowledgeSession authenticationStatelessKSession;
    
    @Autowired
    private AuthenticationCheckPoint authenticationCheckPoint;
    
    public AuthenticationRulesConstraintValidator() {
    }

    @Override
    public void initialize(AuthenticationRuleConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {

    	// Create Errors
        Errors errors = new Errors();
        try {
    
            // Fire rules
        	authenticationStatelessKSession.setGlobal("$service", authenticationCheckPoint);
        	authenticationStatelessKSession.execute(Arrays.asList(new Object[]{errors, target}));
    
            // Check for errors
            if (errors.hasErrors()) {
                // Build constraint violations
            	ValidationMessageBuilder.getInstance(context).buildError(errors);
                return Boolean.FALSE;
            }
        } 
        catch (Exception e) {
            LOG.error("[EXCEPTION]- Authentication validation exception {}", e.getMessage());
            if(e instanceof NullPointerException){
            	errors.addError(target, CommonConstants.EMPTY_STRING, "Oops!!!Some Data Missing");
            	ValidationMessageBuilder.getInstance(context).buildError(errors);
            }else if(e instanceof MongoServerSelectionException){
            	errors.addError(target, CommonConstants.EMPTY_STRING, "Oops!!!Some Internal Error Happened, we are working on it on priority.");
            	ValidationMessageBuilder.getInstance(context).buildError(errors);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

	public void setAuthenticationCheckPoint(
			AuthenticationCheckPoint authenticationCheckPoint) {
		this.authenticationCheckPoint = authenticationCheckPoint;
	}

	public void setAuthenticationStatelessKSession(
			StatelessKnowledgeSession authenticationStatelessKSession) {
		this.authenticationStatelessKSession = authenticationStatelessKSession;
	}
	
}


