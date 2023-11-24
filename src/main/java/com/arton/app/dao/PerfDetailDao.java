package com.arton.app.dao;

import com.arton.app.domain.dto.PerfDetailDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PerfDetailDao {
	private static String namespace = "com.arton.app.dao.PerfDetailMapper.";

	@Autowired
	private SqlSession session;

	/** 공연상세 읽기 */
	public List<PerfDetailDto> selectByPerfId(Integer perfId) {
		return session.selectList(namespace + "selectByPerfId", perfId);
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	public int deleteAllForTest() {
		return session.delete(namespace + "deleteAllForTest");
	}

	public int countForTest() {
		return session.selectOne(namespace + "countForTest");
	}

	public int insertForTest(PerfDetailDto perfDetailDto) {
		return session.insert(namespace + "insertForTest", perfDetailDto);
	}
}
