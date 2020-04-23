package com.robot.app.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@Builder
@ToString
public class Report {

	@Id
	private String id;

	private Date lastReportSend;
	
	private String robotId;
}
