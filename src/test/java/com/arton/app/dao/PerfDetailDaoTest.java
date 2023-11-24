package com.arton.app.dao;

import com.arton.app.domain.dto.PerfDetailDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PerfDetailDaoTest {

	@Autowired
	PerfDetailDao perfDetailDao;

	@Test
	public void selectByPerfId() {
		perfDetailDao.deleteAllForTest();

		int perfId = 1;
		int[] detailIndex = {1, 2, 3};
		perfDetailDao.insertForTest(createPerfDetailDto(perfId, detailIndex[0]));
		perfDetailDao.insertForTest(createPerfDetailDto(perfId, detailIndex[1]));
		perfDetailDao.insertForTest(createPerfDetailDto(perfId, detailIndex[2]));

		List<PerfDetailDto> list = perfDetailDao.selectByPerfId(perfId);
		assertEquals(detailIndex.length, list.size());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	@Test
	public void deleteAllAndCount() {
		perfDetailDao.deleteAllForTest();

		assertEquals(0, perfDetailDao.countForTest());
	}

	@Test
	public void insertForTest() {
		perfDetailDao.deleteAllForTest();

		assertEquals(0, perfDetailDao.countForTest());
		perfDetailDao.insertForTest(createPerfDetailDto(1, 1));
		assertEquals(1, perfDetailDao.countForTest());
	}

	/* Methods for Test */

	/**
	 * 테스트용 PerfDetailDto 생성
	 *
	 * @return PerfDetailDto
	 */
	private PerfDetailDto createPerfDetailDto(int perfId, int detailIndex) {
		return new PerfDetailDto(null, perfId, detailIndex, "path", "path");

	}
}