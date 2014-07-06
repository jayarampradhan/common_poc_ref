/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.validation
 * @createdOn 07-Mar-20142:11:18 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:11:18 AM
 * ***********************************************************************
 */

package com.uimirror.common.bean.validation;

import java.util.Collections;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 07-Mar-20142:11:18 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:11:18 AM
 * ***********************************************************************
 */

public class Collaborators {

    private final Map<String, Object> collaborators;
	
    public Collaborators(Map<String, Object> collaborators) {
	Assert.notNull(collaborators);
	Assert.notEmpty(collaborators);
	this.collaborators = collaborators;
    }

    public Map<String, Object> getCollaborators() {
	return Collections.unmodifiableMap(collaborators);
    }

}


