package com.robot.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReportService {

	public void avgPollutionReport() throws JsonProcessingException;

	public void monitoringStationReport(String stationName) throws JsonProcessingException;
}
