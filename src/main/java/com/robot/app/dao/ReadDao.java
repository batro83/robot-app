package com.robot.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.robot.app.model.Read;

public interface ReadDao extends PagingAndSortingRepository<Read, String> {
	public List<Read> findByTimestampGreaterThan(Date timestamp);
}
