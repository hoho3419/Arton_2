package com.arton.app.dao;

import com.arton.app.domain.dto.SeatInvtDto;
import com.arton.app.domain.request.BookingRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SeatInvtDao {
	private static String namespace = "com.arton.app.dao.SeatInvtMapper.";

	@Autowired
	private SqlSession session;

	/**
	 * <pre>
	 * 예매 시 좌석수 감소(좌석 차감)
	 * - 남은 좌석수가 예매수 보다 적게 남았을 시 좌석차감 실패함
	 * - 후행 작업 => BookingDao의 예매 생성
	 * </pre>
	 *
	 * @return 좌석차감 성공 시 1, 실패 시 0 반환
	 * @see BookingDao#insertBooking
	 */
	public int updateSeatInvtDecreaseSeatStock(BookingRequest bookingRequest) {
		return session.update(namespace + "updateSeatInvtDecreaseSeatStock", bookingRequest);
	}

	/**
	 * <pre>
	 * 예매 취소 시 좌석수 증가(좌석 반환)
	 * - 선행 작업 => BookingDao의 예매 취소.
	 * </pre>
	 *
	 * @see BookingDao#updateBookingStatus
	 */
	public int updateSeatInvtIncreaseSeatStock(BookingRequest bookingRequest) {
		return session.update(namespace + "updateSeatInvtIncreaseSeatStock", bookingRequest);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	public int deleteAllForTest() {
		return session.delete(namespace + "deleteAllForTest");
	}

	public int countForTest() {
		return session.selectOne(namespace + "countForTest");
	}

	public int insertForTest(SeatInvtDto seatInvtDto) {
		return session.insert(namespace + "insertForTest", seatInvtDto);
	}

	public SeatInvtDto selectForTest(Map map) {
		return session.selectOne(namespace + "selectForTest", map);
	}
}
