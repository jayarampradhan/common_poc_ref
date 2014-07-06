package com.uimirror.threadpool;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.collections.CollectionUtils;

public final class CachedPoolService implements Serializable{
    
    private static final long serialVersionUID = 7484579666934298042L;
    
    private final ExecutorService executorService;
    private final int POOL_COUNT;


    private CachedPoolService(int poolCount) {
    	this.POOL_COUNT = poolCount;
    	this.executorService = Executors.newFixedThreadPool(POOL_COUNT);
    }
    
    public static CachedPoolService getInstance(int poolCount){
    	return new CachedPoolService(poolCount);
    }
    

    public void submitTask(List<? extends Callable<Object>> callabels){
    	if(CollectionUtils.isEmpty(callabels)){
    		claimResource();
    		throw new IllegalArgumentException("No Job is there to execute");
    	}
    	for(Callable<Object> callabel : callabels){
    		executorService.submit(callabel);
    	}
    	//claimResource();
    }
    
    public void claimResource(){
    	//always reclaim resources
    	executorService.shutdown();
    }

}
