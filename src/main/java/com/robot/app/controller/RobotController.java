package com.robot.app.controller;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robot.app.service.ProcessService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/robot")
public class RobotController {

	private static final Logger LOG = getLogger(RobotController.class);

	@Autowired
	private ProcessService process;

	@ApiOperation(value = "Start robot.")
	@PostMapping("/start/{polyline}")
	public ResponseEntity<HttpStatus> startRobot(@PathVariable("polyline") String polyline) throws Exception {
		LOG.debug("RobotController - startRobot: {}", polyline);
		process.process(polyline);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Stop robot.")
	@PostMapping("/stop")
	public ResponseEntity<HttpStatus> stopRobot() throws Exception {
		LOG.debug("RobotController - stopRobot: {}");
		// TODO
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}
}