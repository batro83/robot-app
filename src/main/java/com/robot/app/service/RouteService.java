package com.robot.app.service;

import java.util.List;

import com.google.maps.model.LatLng;

public interface RouteService {

	public List<LatLng> getRouteLocationList(String polyline);
	
	public float getDistanceBetweenPositions(LatLng poly1, LatLng poly2);
}
