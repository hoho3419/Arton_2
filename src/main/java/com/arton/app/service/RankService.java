package com.arton.app.service;

import com.arton.app.dao.PerfDao;
import com.arton.app.domain.RankingCondition;
import com.arton.app.domain.dto.PerfDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {
	@Autowired
	PerfDao perfDao;

	/** 조건에 맞는 랭킹 가져오기 */
	public List<PerfDto> readRank(Integer cateCode, String rankDate, String period) {
		System.out.println(rankDate);
		return ServiceMethods.stringToDateFormat(perfDao.selectPerfsOrderByBookingCount(RankingCondition.toRankingCondition(
				cateCode,
				rankDate,
				period)));
	}

}
