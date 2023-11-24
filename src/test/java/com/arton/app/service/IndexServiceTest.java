package com.arton.app.service;

import com.arton.app.domain.dto.PerfDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class IndexServiceTest {

	@Autowired
	IndexService indexService;

	@Test
	public void readMainSlide() {
		List<PerfDto> list = indexService.readMainSlide();
		list.forEach(System.out::println);
	}

	@Test
	public void readRankSection() {
		System.out.println(indexService.readRankSection());
	}
}