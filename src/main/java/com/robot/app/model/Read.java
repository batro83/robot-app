package com.robot.app.model;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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

	@NotNull
	private Date timestamp;

	@NotNull
	private LatLng location;

	@NotNull
	private double level;

	@NotNull
	private String source;

}
