package com.robot.app.dto;

import java.util.Date;

import com.google.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

	private Date timestamp;

	private LatLng location;

	private String level;

	private String source;

}