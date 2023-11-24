package com.arton.app.service;

import com.arton.app.dao.PerfDao;
import com.arton.app.domain.RankingCondition;
import com.arton.app.domain.dto.PerfDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {

	@Autowired
	PerfDao perfDao;

	/** 메인 슬라이드 섹션 */
	public List<PerfDto> readMainSlide() {
		Map map = new HashMap();
		map.put("where", "AND (id = 1096 OR id = 1091 OR id = 1056 OR id = 1086 OR id = 1081 OR id = 1060)");

		return ServiceMethods.stringToDateFormat(perfDao.selectPerfsByCustomizedCondition(map));
	}

	/** Rank 섹션 */
	public Map<String, List<PerfDto>> readRankSection() {
		Map<String, List<PerfDto>> map = new HashMap<>();

		map.put("rankCate1",
				ServiceMethods.stringToDateFormat(perfDao.selectPerfsOrderByBookingCount(RankingCondition.toRankingCondition(
						1,
						null,
						"y"))));

		map.put("rankCate2",
				ServiceMethods.stringToDateFormat(perfDao.selectPerfsOrderByBookingCount(RankingCondition.toRankingCondition(
						2,
						null,
						"y"))));

		map.put("rankCate3",
				ServiceMethods.stringToDateFormat(perfDao.selectPerfsOrderByBookingCount(RankingCondition.toRankingCondition(
						3,
						null,
						"y"))));

		return map;
	}

}
