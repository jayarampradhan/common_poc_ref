/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.bean
 * @createdOn 12-Mar-20141:16:00 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:16:00 am
 * ***********************************************************************
 */

package com.uimirror.api.common.bean;

import java.io.Serializable;
import java.util.Map;

import com.uimirror.api.common.annotation.VerificationRuleConstraint;
import com.uimirror.common.bean.DroolsDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Holds the property for the verify form bean
 * like token, email, source and some user attributes like time zone
 * locale etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Mar-20141:16:00 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:16:00 am
 * ***********************************************************************
 */
@VerificationRuleConstraint
public class VerifyBean extends DroolsDto implements Serializable{

    private static final long serialVersionUID = -2444166666432068186L;

    public VerifyBean(Map<String, Object> fact) {
		super(fact);
	}
    

}


