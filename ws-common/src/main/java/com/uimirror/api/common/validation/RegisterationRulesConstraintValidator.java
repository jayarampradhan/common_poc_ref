/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.validation
 * @createdOn 07-Mar-20142:06:37 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:06:37 AM
 * ***********************************************************************
 */

package com.uimirror.api.common.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoServerSelectionException;
import com.uimirror.api.common.annotation.RegisterationRuleConstraint;
import com.uimirror.api.common.register.service.RegisterCheckPoint;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.bean.validation.Errors;
import com.uimirror.common.util.ValidationMessageBuilder;
/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : validates rules defined in the drl for name, 
 * email, sex, DOB etc. 
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 07-Mar-20142:06:37 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:06:37 AM
 * ***********************************************************************
 */

public class RegisterationRulesConstraintValidator implements ConstraintValidator<RegisterationRuleConstraint, Object>{
    
    protected static final Logger LOG = LoggerFactory.getLogger(RegisterationRulesConstraintValidator.class);
    
    @Autowired
    private StatelessKnowledgeSession registrationStatelessKSession;
    
    @Autowired
    private RegisterCheckPoint registerCheckPoint;
    
//    @Autowired
//    private Collaborators collaborators;

    @Override
    public void initialize(RegisterationRuleConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
	
    	// Create Errors
        Errors errors = new Errors();
   
        try {
    
            // Fire rules
        	registrationStatelessKSession.setGlobal("$service", registerCheckPoint);
            registrationStatelessKSession.execute(Arrays.asList(new Object[]{errors, target}));
    
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

	public void setRegistrationStatelessKSession(
			StatelessKnowledgeSession registrationStatelessKSession) {
		this.registrationStatelessKSession = registrationStatelessKSession;
	}

	public void setRegisterCheckPoint(RegisterCheckPoint registerCheckPoint) {
		this.registerCheckPoint = registerCheckPoint;
	}

}


