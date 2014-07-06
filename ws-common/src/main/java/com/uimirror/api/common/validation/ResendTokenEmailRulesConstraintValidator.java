package com.uimirror.api.common.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoServerSelectionException;
import com.uimirror.api.common.annotation.ResendTokenEmailRuleConstraint;
import com.uimirror.api.common.register.service.ResendTokenEmailCheckPoint;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.bean.validation.Errors;
import com.uimirror.common.util.ValidationMessageBuilder;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : validates rules defined in the drl for profile id, 
 * email etc. .
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 30-Mar-2014 1:37:10 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:37:10 AM
 * ***********************************************************************
 */
public class ResendTokenEmailRulesConstraintValidator implements ConstraintValidator<ResendTokenEmailRuleConstraint, Object>{
	
	protected static final Logger LOG = LoggerFactory.getLogger(ResendTokenEmailRulesConstraintValidator.class);
	
	@Autowired
    private StatelessKnowledgeSession resendTokenEmailStatelessKSession;
	
	@Autowired
	private ResendTokenEmailCheckPoint resendTokenEmailCheckPoint;

	@Override
	public void initialize(ResendTokenEmailRuleConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Object target, ConstraintValidatorContext context) {
		// Create Errors
        Errors errors = new Errors();
   
        try {
    
            // Fire rules
        	resendTokenEmailStatelessKSession.setGlobal("$service", resendTokenEmailCheckPoint);
            resendTokenEmailStatelessKSession.execute(Arrays.asList(new Object[]{errors, target}));
    
            // Check for errors
            if (errors.hasErrors()) {
            	// Build constraint violations
            	ValidationMessageBuilder.getInstance(context).buildError(errors);
                return Boolean.FALSE;
            }
        } 
        catch (Exception e) {
            LOG.error(e.toString());
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

}
