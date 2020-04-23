package com.robot.app.utils;

public enum PollutionLevel {
	GOOD("Good"), MODERATE("Moderate"), USG("USG"), UNHEALTHY("Unhealthy");

	private String value;

	PollutionLevel(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
