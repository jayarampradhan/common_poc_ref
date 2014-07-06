/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.transformer
 * @createdOn 10-Apr-2014 1:27:10 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 1:27:10 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.transformer;

import java.util.Arrays;

import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uimirror.common.transformer.Transformer;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will validate the user document path 
 * with drools engine.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Apr-2014 1:27:10 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 1:27:10 PM
 * ***********************************************************************
 */
@Component(value="usrDocTransformer")
public class UserDocumentStateTransformer implements Transformer{

	protected static final Logger LOG = LoggerFactory.getLogger(UserDocumentStateTransformer.class);
	
	@Autowired
    private StatelessKnowledgeSession userBasicDocumentStatelessKSession;

	@Override
	public Object doTransform(Object obj) {
		try {
            // Fire rules
        	//registrationStatelessKSession.setGlobal("$service", registerCheckPoint);
        	userBasicDocumentStatelessKSession.execute(Arrays.asList(new Object[]{obj}));
        } 
        catch (Exception e) {
            LOG.error("[EXCEPTION]- Validating User Document has {}",e.toString());
            return Boolean.FALSE;
        }
		return obj;
	}
	
	public void setUserBasicDocumentStatelessKSession(StatelessKnowledgeSession userBasicDocumentStatelessKSession) {
		this.userBasicDocumentStatelessKSession = userBasicDocumentStatelessKSession;
	}
	
}
