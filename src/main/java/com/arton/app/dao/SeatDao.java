package com.arton.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeatDao {
	private static String namespace = "com.arton.app.dao.SeatMapper.";
	@Autowired
	private SqlSession session;

	/* 관리자 기능 외에 필요한 기능 없음 */

}
