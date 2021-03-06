package com.robot.app.test.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.maps.model.LatLng;
import com.robot.app.config.RobotConfig;
import com.robot.app.model.Read;
import com.robot.app.service.ReadService;
import com.robot.app.service.RouteService;
import com.robot.app.service.impl.ProcessServiceImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessServiceTest {

	@Mock
	private RouteService routeService;
	@Mock
	private RobotConfig robotConfig;
	@Mock
	private ReadService readService;
	@InjectMocks
	private ProcessServiceImpl processService;

	@Test
	public void test001_process_ok() throws InterruptedException, JsonProcessingException {
		when(robotConfig.getSpeed()).thenReturn(100f);
		List<LatLng> mockList = new ArrayList<>();
		mockList.add(new LatLng());
		mockList.add(new LatLng());

		when(routeService.getRouteLocationList(any())).thenReturn(mockList);
		when(routeService.getDistanceBetweenPositions(any(), any())).thenReturn(115f);

		processService.process("polyline");

		verify(readService, times(2)).saveRead(any(Read.class));
	}

	@Test
	public void test002_process_NoPositionList() throws InterruptedException, JsonProcessingException {
		when(robotConfig.getMetersCollect()).thenReturn(100f);
		when(robotConfig.getSpeed()).thenReturn(1000f);
		when(routeService.getRouteLocationList(any())).thenReturn(Collections.emptyList());
		when(routeService.getDistanceBetweenPositions(any(), any())).thenReturn(101f);

		processService.process("polyline");

		verify(readService, times(0)).saveRead(any(Read.class));
	}
}