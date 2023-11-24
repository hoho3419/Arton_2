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
public class CateService {

	@Autowired
	PerfDao perfDao;

	/** 카테 메인 슬라이드 - 랜덤 */
	public List<PerfDto> readMainSlide(Integer cateCode) {
		Map map = new HashMap();
		map.put("where", "AND cate = '" + cateCodeToCateString(cateCode) + "'");
		map.put("order", "ORDER BY RAND()");
		map.put("limit", "LIMIT 0, 5");

		return ServiceMethods.stringToDateFormat(perfDao.selectPerfsByCustomizedCondition(map));
	}

	/** What's Hot 섹션 - 찜랭킹 */
	public List<PerfDto> readLikeCountRank(Integer cateCode) {
		return ServiceMethods.stringToDateFormat(perfDao.selectPerfsOrderByLikeCount(cateCode));
	}

	/** Weekly Ranking - 랭킹(주간) */
	public List<PerfDto> readWeeklyRank(Integer cateCode) {
		RankingCondition rc = new RankingCondition(cateCode, null, "w");
		return ServiceMethods.stringToDateFormat(perfDao.selectPerfsOrderByBookingCount(rc));
	}

	/** Arton's Pick - 미리 정해둔 리스트(id 순서로 8개) */
	public List<PerfDto> readArtonPick(Integer cateCode) {
		Map map = new HashMap();
		map.put("where", "AND cate = '" + cateCodeToCateString(cateCode) + "'");
		map.put("order", "ORDER BY id");
		map.put("limit", "LIMIT 0, 8");

		return ServiceMethods.stringToDateFormat(perfDao.selectPerfsByCustomizedCondition(map));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private String cateCodeToCateString(Integer cateCode) {
		String[] arr = {"콘서트", "뮤지컬", "연극", "콘서트", "콘서트", "콘서트"};
		return arr[cateCode - 1];
	}
}
