package com.uimirror.threadpool;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;

public final class CompletationFixedPoolService implements Serializable{
    
    private static final long serialVersionUID = 7484579666934298042L;
    
	private final int POOL_COUNT;
	private final ExecutorService executorService;
	private final ExecutorCompletionService<Object> completionService;

	private CompletationFixedPoolService(int poolCount) {
		this.POOL_COUNT = poolCount;
		this.executorService = Executors.newFixedThreadPool(POOL_COUNT);
		this.completionService = new ExecutorCompletionService<Object>(
				this.executorService);
	}

	public static CompletationFixedPoolService getInstance(int poolCount) {
		return new CompletationFixedPoolService(poolCount);
	}

	public void submitTask(List<? extends Callable<Object>> callabels)
			throws IllegalArgumentException {

		if (CollectionUtils.isEmpty(callabels)) {
			claimResource();
			throw new IllegalArgumentException("No Job is there to execute");
		}

		for (Callable<Object> callabel : callabels) {
			completionService.submit(callabel);
		}
	}

	public Object[] getResults() throws InterruptedException,
			ExecutionException {

		Object[] results = new Object[POOL_COUNT];
		for (int i = 0; i < POOL_COUNT; i++) {
			results[i] = completionService.take().get();
		}
		claimResource();
		return results;
	}

	public void claimResource() {
		// always reclaim resources
		executorService.shutdown();
	}

}
