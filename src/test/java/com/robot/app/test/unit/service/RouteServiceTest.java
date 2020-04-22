package com.robot.app.test.unit.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.model.LatLng;
import com.robot.app.service.impl.RouteServiceImpl;

import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RouteServiceTest {

	@InjectMocks
	private RouteServiceImpl routeService;

	@Test
	public void test001_getRouteLocationList_ok() {
		String polyline = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGu\n" + 
				"AD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa\n" + 
				"@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkI\n" + 
				"CmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^y\n" + 
				"VJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSM\n" + 
				"GBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@\n" + 
				"a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iD\n" + 
				"q@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQ\n" + 
				"KkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
		List<LatLng> list = routeService.getRouteLocationList(polyline);
		assertTrue(list.size() > 0);
	}

	@Test
	public void test002_getRouteLocationList_ko() {
		String polyline = "ffff";
		List<LatLng> list = routeService.getRouteLocationList(polyline);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void test003_getDistanceBetweenPolylines_ok() {
		LatLng pos1 = new LatLng(51.49965, -0.21333);
		LatLng pos2 = new LatLng(51.5004, -0.21405);
		float distance = routeService.getDistanceBetweenPolylines(pos1, pos2);
		assertTrue(distance > 0);
	}
	
	@Test
	public void test004_getDistanceBetweenPolylines_ko() {
		LatLng pos1 = new LatLng(51.49965, -0.21333);
		LatLng pos2 = null;
		float distance = routeService.getDistanceBetweenPolylines(pos1, pos2);
		assertEquals(0, distance);
	}
}