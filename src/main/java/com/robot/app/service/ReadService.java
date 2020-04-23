package com.robot.app.service;

import java.util.Date;
import java.util.List;

import com.robot.app.model.Read;

public interface ReadService {

	public void saveRead(Read read);

	public List<Read> getReads(Date date);
	
	public List<Read> getAllReads();

}
