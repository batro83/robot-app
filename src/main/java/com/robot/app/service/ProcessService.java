package com.robot.app.service;

import java.util.concurrent.CompletableFuture;

public interface ProcessService {

	public CompletableFuture<Boolean> process(String polyline) throws InterruptedException;
	public void stop();
}
