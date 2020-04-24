package com.robot.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.robot.app.config.model.MonitoringStations;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix="robot")
public class RobotConfig {

	private float speed;
	
	private float metersCollect;
	
	private List<MonitoringStations> monitoringStations = new ArrayList<>();
}
