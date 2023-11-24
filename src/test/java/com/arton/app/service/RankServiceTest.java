package com.arton.app.service;

import com.arton.app.domain.dto.PerfDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RankServiceTest {

	@Autowired
	RankService rankService;

	@Test
	public void readRank() {
		List<PerfDto> list = rankService.readRank(1, "2023-01-23", "w");

		assertNotNull(list);
		assertTrue(list.get(0).getBookingCnt() >= list.get(1).getBookingCnt());
		assertTrue(list.get(1).getBookingCnt() >= list.get(2).getBookingCnt());
		assertTrue(list.get(2).getBookingCnt() >= list.get(3).getBookingCnt());

		//		System.out.println(list);
	}
}