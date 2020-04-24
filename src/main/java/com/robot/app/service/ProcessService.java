package com.robot.app.service;

import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProcessService {

	public CompletableFuture<Boolean> process(String polyline) throws InterruptedException, JsonProcessingException;
	public void stop();
}
