package com.robot.app.service.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import com.robot.app.service.RouteService;

/**
 * Service about robot's routes
 * 
 * @author batro
 *
 */
@Service
public class RouteServiceImpl implements RouteService {

	private static final Logger LOG = getLogger(RouteServiceImpl.class);

	@Override
	public List<LatLng> getRouteLocationList(String polyline) {
		List<LatLng> routesList = null;
		try {
			routesList = PolylineEncoding.decode(polyline);
		} catch (Exception e) {
			LOG.error("Polyline string is invalid!: {}", polyline);
			routesList = Collections.emptyList();
		}
		return routesList;
	}

	@Override
	public float getDistanceBetweenPositions(LatLng position1, LatLng position2) {
		if (position1 == null || position2 == null) {
			return 0f;
		}
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(position2.lat - position1.lat);
		double dLng = Math.toRadians(position2.lng - position1.lng);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(position1.lat))
				* Math.cos(Math.toRadians(position2.lat)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (float) (earthRadius * c);
	}
}
