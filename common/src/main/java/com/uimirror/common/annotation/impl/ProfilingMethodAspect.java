/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.annotation.impl
 * @createdOn 19-Mar-2014 2:28:57 AM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 2:28:57 AM
 * ***********************************************************************
 */
package com.uimirror.common.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.uimirror.common.annotation.ProfileExecution;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the implementation for the stop watch.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 19-Mar-2014 2:28:57 AM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 2:28:57 AM
 * ***********************************************************************
 */
@Aspect
public class ProfilingMethodAspect{
	
	protected static final Logger LOG = LoggerFactory.getLogger(ProfilingMethodAspect.class);

	@Around("execution(* *(..)) && @annotation(profileExecution)")
	public Object applyStopWatch(ProceedingJoinPoint joinPoint, ProfileExecution profileExecution) throws Throwable {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		final StopWatch stopWatch = new StopWatch(methodName);
        stopWatch.start(methodName);

        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            LOG.debug(StringUtility.formatStopSwatchSummery(methodName, stopWatch));
        }
	}


}
