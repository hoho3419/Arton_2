package com.arton.app.dao;

import com.arton.app.domain.RankingCondition;
import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.ViewSeatInvtDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PerfDao {
	private static String namespace = "com.arton.app.dao.PerfMapper.";
	@Autowired
	private SqlSession session;

	/** DealPage 공연 1개 읽기(공연정보 + 기타 집계정보) */
	public PerfDto selectPerfByPerfId(Integer perfId) {
		return session.selectOne(namespace + "selectPerfByPerfId", perfId);
	}

	/** DealPage 예매 섹션 좌석 리스트 읽기 */
	public List<ViewSeatInvtDto> selectSeatInvtsByPerfId(Integer perfId) {
		return session.selectList(namespace + "selectSeatInvtsByPerfId", perfId);
	}

	/** 공연 List 읽기(map 으로 where, order, limit 조건 직접 작성) */
	public List<PerfDto> selectPerfsByCustomizedCondition(Map map) {
		return session.selectList(namespace + "selectPerfsByCustomizedCondition", map);
	}

	/** 예매 랭킹 List 읽기 */
	public List<PerfDto> selectPerfsOrderByBookingCount(RankingCondition rc) {
		return session.selectList(namespace + "selectPerfsOrderByBookingCount", rc);
	}

	/** 찜 랭킹 List 읽기 */
	public List<PerfDto> selectPerfsOrderByLikeCount(Integer cateCode) {
		return session.selectList(namespace + "selectPerfsOrderByLikeCount", cateCode);
	}

	/** 마이페이지 찜 목록 */
	public List<PerfDto> selectWishlistsByUserIdx(Integer userIdx) {
		return session.selectList(namespace + "selectWishlistsByUserIdx", userIdx);
	}

}
