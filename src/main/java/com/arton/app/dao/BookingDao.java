package com.arton.app.dao;

import com.arton.app.domain.dto.BookingDto;
import com.arton.app.domain.request.BookingRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookingDao {
	private static String namespace = "com.arton.app.dao.BookingMapper.";

	@Autowired
	private SqlSession session;

	/**
	 * <pre>
	 * 예매 생성
	 * - 선행 작업 => SeatInvtDao 좌석차감 먼저 실행.
	 * </pre>
	 *
	 * @see SeatInvtDao#updateSeatInvtDecreaseSeatStock
	 */
	public int insertBooking(BookingRequest bookingRequest) {
		return session.insert(namespace + "insertBooking", bookingRequest);
	}

	/**
	 * <pre>
	 * 예매 취소
	 * - 후행 작업 => SeatInvtDao에서 취소한 좌석 반환하기(좌석 수 증가).
	 * </pre>
	 *
	 * @see SeatInvtDao#updateSeatInvtIncreaseSeatStock
	 */
	public int updateBookingStatus(Integer bookingId) {
		return session.update(namespace + "updateBookingStatus", bookingId);
	}


	/** 마이 페이지 예매내역 1개 읽기 */
	public BookingDto selectBookingById(Integer bookingId) {
		return session.selectOne(namespace + "selectBookingById", bookingId);
	}

	/** 마이페이지 예매내역 목록 읽기 */
	public List<BookingDto> selectBookingsByUserIdx(Integer userIdx) {
		return session.selectList(namespace + "selectBookingsByUserIdx", userIdx);
	}

	/**
	 * 예매 여부 확인(댓글 작성 시)
	 *
	 * @return int 리턴값 1 이상일 시 댓글 작성 가능
	 */
	public int countBookingStatusByUserIdxAndPerfId(Map map) {
		return session.selectOne(namespace + "countBookingStatusByUserIdxAndPerfId", map);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	//		public int deleteAllForTest() {
	//			return session.delete(namespace + "deleteAllForTest");
	//		}

	public int countForTest() {
		return session.selectOne(namespace + "countForTest");
	}

}
