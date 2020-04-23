package com.robot.app.service;

public interface ProcessService {

	public void process(String polyline) throws InterruptedException;
	public void stop();
}
