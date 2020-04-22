package com.robot.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.maps.model.LatLng;
import com.robot.app.model.Read;
import com.robot.app.service.ProcessService;
import com.robot.app.service.ReadService;
import com.robot.app.service.RouteService;

/**
 * Service to simulate the robot
 * @author batro
 *
 */
@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private RouteService routeService;
	@Autowired
	private ReadService readService;

	@Async("processExecutor")
	@Override
	public void process(String polyline) {
		float counterMeters = 0f;
		List<LatLng> routeList = routeService.getRouteLocationList(polyline);

		LatLng prevPosition = null;
		for (int i = 0; i < routeList.size(); i++) {
			LatLng actualPosition = routeList.get(i);
			float distance = routeService.getDistanceBetweenPolylines(actualPosition, prevPosition);
			counterMeters += distance;
			if (counterMeters >= 100) {
				readService.saveRead(collectData(actualPosition));
				counterMeters = 0f;
			}
			prevPosition = actualPosition;
		}
	}

	private Read collectData(LatLng position) {
		return Read.builder()
			.timestamp(new Date())
			.location(position)
			.level(new Random().ints(1, 0, 201).findFirst().getAsInt())
			.source("robot")
			.build();
	}

}
