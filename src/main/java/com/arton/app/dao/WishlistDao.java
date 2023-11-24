package com.arton.app.dao;

import com.arton.app.domain.dto.WishlistDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class WishlistDao {
	private static String namespace = "com.arton.app.dao.WishlistMapper.";

	@Autowired
	private SqlSession session;

	/** 사용자의 해당 공연 찜 여부 확인(딜 페이지) */
	public WishlistDto selectWishlistByUserIdxAndPerfId(Map map) {
		return session.selectOne(namespace + "selectWishlistByUserIdxAndPerfId", map);
	}

	/** 찜하기 */
	public int insertWishlist(Map map) {
		return session.insert(namespace + "insertWishlist", map);
	}

	/** 찜하기 취소 */
	public int deleteWishlist(Map map) {
		return session.delete(namespace + "deleteWishlist", map);
	}

	/** 해당 공연 찜 갯수만 가져오기(Ajax 갱신) */
	public int countWishlistByPerfId(Integer perfId) {
		return session.selectOne(namespace + "countWishlistByPerfId", perfId);
	}


	/////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */

	public int deleteAllForTest() {
		return session.delete(namespace + "deleteAllForTest");
	}

	public int countForTest() {
		return session.selectOne(namespace + "countForTest");
	}

}
