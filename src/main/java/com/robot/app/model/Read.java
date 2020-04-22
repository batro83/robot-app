package com.robot.app.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.maps.model.LatLng;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@Builder
@ToString
public class Read {

	@Id
	private String id;

	private Date timestamp;

	private LatLng location;

	private double level;

	private String source;

}
