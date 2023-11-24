package com.arton.app.service;

import com.arton.app.domain.dto.ViewSeatInvtDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DealServiceTest {

	@Autowired
	DealService dealService;

	@Test
	public void readSeatInfo() {
		Integer perfCode = 1001;
		Map<Integer, Map<Integer, List<ViewSeatInvtDto>>> map = dealService.readSeatInfo(perfCode);

		assertNotNull(map);
		//		System.out.println(map);
	}

}