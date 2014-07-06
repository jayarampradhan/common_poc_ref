/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.validation
 * @createdOn 10-Apr-2014 1:55:19 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 1:55:19 PM
 * ***********************************************************************
 */
package com.uimirror.common.transformer.command;

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
 * Details of class goes like : This will Transform the commands with 
 * drool rule engine and set into state of the object.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Apr-2014 1:55:19 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 1:55:19 PM
 * ***********************************************************************
 */
@Component(value="cmdStateTransformer")
public class CommandStateTransformer implements Transformer{

	protected static final Logger LOG = LoggerFactory.getLogger(CommandStateTransformer.class);
	
	@Autowired
    private StatelessKnowledgeSession commandStatelessKSession;

	@Override
	public Object doTransform(Object target) {
		try {
            // Fire rules
        	commandStatelessKSession.execute(Arrays.asList(new Object[]{target}));
        }catch (Exception e) {
            LOG.error("[EXCEPTION]- Transforming command state {}",e.toString());
        }
		return target;
	}

}
