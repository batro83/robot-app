package com.robot.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.robot.app.model.Report;

public interface ReportDao extends PagingAndSortingRepository<Report, String> {

	public Report findByRobotId(String robotId);

}
