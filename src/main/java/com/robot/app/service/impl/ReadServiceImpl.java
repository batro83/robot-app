package com.robot.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robot.app.dao.ReadDao;
import com.robot.app.model.Read;
import com.robot.app.service.ReadService;

/**
 * Service to collect and manage pollution reads
 * @author batro
 *
 */
@Service
public class ReadServiceImpl implements ReadService {

	@Autowired
	private ReadDao readDao;

	@Override
	public void saveRead(Read read) {
		readDao.save(read);
	}

	@Override
	public List<Read> getReads(Date timestamp) {
		return readDao.findByTimestampGreaterThan(timestamp);
	}
}