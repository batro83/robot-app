package com.robot.app.service.impl;

import static java.util.Optional.ofNullable;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robot.app.dao.ReportDao;
import com.robot.app.dto.ReportDto;
import com.robot.app.model.Read;
import com.robot.app.model.Report;
import com.robot.app.service.ReadService;
import com.robot.app.service.ReportService;
import com.robot.app.utils.PollutionLevel;

/**
 * Service to manage robot's reports
 * 
 * @author batro
 *
 */
@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger LOG = getLogger(ReportServiceImpl.class);

	@Autowired
	private ReadService readService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ReportDao reportDao;

	@Scheduled(cron = "* 0/15 * * * ?")
	@Override
	public void avgPollutionReport() throws JsonProcessingException {
		Report lastReport = getLastReportSend();
		List<Read> listToReport = getReadsToReport(lastReport.getLastReportSend());
		if (!listToReport.isEmpty()) {
			Read lastRead = listToReport.get(listToReport.size() - 1);

			double avg = listToReport.stream().mapToDouble(Read::getLevel).average().getAsDouble();
			ReportDto report = modelMapper.map(lastRead, ReportDto.class);
			report.setLevel(getPollutionLevel(avg));
			LOG.info(objectMapper.writeValueAsString(report));

			lastReport.setLastReportSend(lastRead.getTimestamp());
			saveLastReportSend(lastReport);
		}
	}

	private List<Read> getReadsToReport(Date lastReport) {
		return ofNullable(lastReport).map(x -> readService.getReads(x)).orElse(readService.getAllReads());
	}

	private String getPollutionLevel(double avg) {
		String level = null;
		if (avg > 0 && avg <= 50) {
			level = PollutionLevel.GOOD.getValue();
		} else if (avg > 51 && avg <= 100) {
			level = PollutionLevel.MODERATE.getValue();
		} else if (avg > 101 && avg <= 150) {
			level = PollutionLevel.USG.getValue();
		} else if (avg > 150) {
			level = PollutionLevel.UNHEALTHY.getValue();
		}
		return level;
	}

	private Report getLastReportSend() {
		return ofNullable(reportDao.findByRobotId("robot")).orElse(Report.builder().build());
	}

	private void saveLastReportSend(Report report) {
		if (report.getId() == null) {
			report.setRobotId("robot");
		}
		reportDao.save(report);
	}
}
