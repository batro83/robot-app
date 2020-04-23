package com.robot.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix="robot")
public class RobotConfig {

	private float speed;
	
	private float metersCollect;
}
