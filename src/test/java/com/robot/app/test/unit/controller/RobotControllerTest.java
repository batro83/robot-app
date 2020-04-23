package com.robot.app.test.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.robot.app.controller.RobotController;
import com.robot.app.service.ProcessService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RobotControllerTest {

	@Mock
	private ProcessService process;

	@InjectMocks
	private RobotController robotController;

	@Test
	public void test001_getWorker() throws Exception {
		final String polyline1 = "";
		ResponseEntity<HttpStatus> response = robotController.startRobot(polyline1);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
