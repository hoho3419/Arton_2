package com.arton.app.dao;

import com.arton.app.domain.dto.ReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReviewDao {
	private static String namespace = "com.arton.app.dao.ReviewMapper.";
	@Autowired
	private SqlSession session;

	//   추후 추가 작업 필요


	public int insert(ReviewDto reviewDto) {
		return session.insert(namespace + "insert", reviewDto);
	}

	public ReviewDto select(Map map) {
		return session.selectOne(namespace + "select", map);
	}

	public List<ReviewDto> selectAll(Integer perfId) {
		return session.selectList(namespace + "selectAll", perfId);
	}

	public int count(Integer perfId) {
		return session.selectOne(namespace + "count", perfId);
	}

	public int update(ReviewDto reviewDto) {
		return session.update(namespace + "update", reviewDto);
	}

	public int delete(Map map) {
		return session.delete(namespace + "delete", map);
	}

	/* ******************************************************************** */
	/* for Test */
	public int deleteAllForTest() {
		return session.delete(namespace + "deleteAllForTest");
	}

	public int countForTest() {
		return session.selectOne(namespace + "countForTest");
	}
	/* ******************************************************************** */

}
