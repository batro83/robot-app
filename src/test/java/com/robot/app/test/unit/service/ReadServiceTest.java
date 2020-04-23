package com.robot.app.test.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.robot.app.dao.ReadDao;
import com.robot.app.model.Read;
import com.robot.app.service.impl.ReadServiceImpl;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReadServiceTest {
	
	@Mock
	private ReadDao readDao;
	
	@InjectMocks
	private ReadServiceImpl readService;
	
	@Test
	public void test001_saveRead_ok() {
		readService.saveRead(Read.builder().build());
		verify(readDao).save(any(Read.class));
	}
	
	@Test
	public void test002_getReads_ok() {
		when(readDao.findByTimestampGreaterThan(any())).thenReturn(Collections.emptyList());
		readService.getReads(new Date());
		verify(readDao).findByTimestampGreaterThan(any());		
	}
	
	@Test
	public void test003_allReads_ok() {
		when(readDao.findAll()).thenReturn(Collections.emptyList());
		readService.getAllReads();
		verify(readDao).findAll();		
	}
}