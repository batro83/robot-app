package com.robot.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.maps.model.LatLng;
import com.robot.app.config.RobotConfig;
import com.robot.app.config.model.MonitoringStations;
import com.robot.app.model.Read;
import com.robot.app.service.ProcessService;
import com.robot.app.service.ReadService;
import com.robot.app.service.ReportService;
import com.robot.app.service.RouteService;

/**
 * Service to simulate the robot process
 * 
 * @author batro
 *
 */
@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private RouteService routeService;
	@Autowired
	private ReadService readService;
	@Autowired
	private RobotConfig robotConfig;
	@Autowired
	private ReportService reportService;

	@Async("singleThreaded")
	@Override
	public CompletableFuture<Boolean> process(String polyline) throws InterruptedException, JsonProcessingException {
		float counterMeters = 0f;
		List<LatLng> routeList = routeService.getRouteLocationList(polyline);
		LatLng prevPosition = null;
		for (int i = 0; i < routeList.size(); i++) {
			LatLng actualPosition = routeList.get(i);
			float distance = routeService.getDistanceBetweenPositions(actualPosition, prevPosition);
			counterMeters += distance;

			checkMonitoringStations(actualPosition);

			if (counterMeters >= robotConfig.getMetersCollect()) {
				readService.saveRead(collectData(actualPosition));
				counterMeters = 0f;
			}
			prevPosition = actualPosition;
			Thread.sleep((long) ((distance / robotConfig.getSpeed()) * 1000));
		}
		return CompletableFuture.completedFuture(true);
	}

	@Override
	public void stop() {

	}

	private Read collectData(LatLng position) {
		return Read.builder()
			.timestamp(new Date())
			.location(position)
			.level(new Random().ints(1, 0, 201).findFirst().getAsInt())
			.source("robot")
			.build();
	}

	private void checkMonitoringStations(LatLng actualPosition) throws JsonProcessingException {
		List<MonitoringStations> stations = robotConfig.getMonitoringStations();

		for (MonitoringStations station : stations) {
			LatLng stationPosition = new LatLng(station.getLat(), station.getLng());
			float distance = routeService.getDistanceBetweenPositions(actualPosition, stationPosition);
			if (distance <= 100) {
				reportService.monitoringStationReport(station.getName());
			}
		}
	}
}
