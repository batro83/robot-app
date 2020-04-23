package com.robot.app.test.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import com.robot.app.dao.ReportDao;
import com.robot.app.dto.ReportDto;
import com.robot.app.model.Read;
import com.robot.app.model.Report;
import com.robot.app.service.ReadService;
import com.robot.app.service.impl.ReportServiceImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportTest {

	@Mock
	private ReadService readService;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private ObjectMapper objectMapper;
	@Mock
	private ReportDao reportDao;
	@InjectMocks
	private ReportServiceImpl reportService;

	@Test
	public void test001_avgPollutionReport_firstTime_ok() throws JsonProcessingException {
		when(reportDao.findByRobotId(any())).thenReturn(Report.builder().build());

		Read read1 = Read.builder().level(50d).timestamp(new Date()).build();
		Read read2 = Read.builder().level(60d).timestamp(new Date()).build();
		when(readService.getAllReads()).thenReturn(Arrays.asList(read1, read2));

		LatLng location1 = new LatLng(1, 1);
		ReportDto dto = ReportDto.builder().timestamp(new Date()).location(location1).build();
		when(modelMapper.map(any(), any())).thenReturn(dto);

		reportService.avgPollutionReport();

		verify(reportDao).save(any());
	}

	@Test
	public void test002_avgPollutionReport_emptyReads_ok() throws JsonProcessingException {
		when(reportDao.findByRobotId(any())).thenReturn(Report.builder().build());
		when(readService.getAllReads()).thenReturn(Collections.emptyList());

		reportService.avgPollutionReport();

		verify(reportDao, times(0)).save(any());
	}
}