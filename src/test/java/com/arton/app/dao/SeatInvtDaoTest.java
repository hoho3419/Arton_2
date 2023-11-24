package com.arton.app.dao;

import com.arton.app.domain.dto.SeatInvtDto;
import com.arton.app.domain.request.BookingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SeatInvtDaoTest {

	@Autowired
	SeatInvtDao seatInvtDao;


	@Test
	public void updateSeatInvtDecreaseSeatStock() {
		seatInvtDao.deleteAllForTest();

		SeatInvtDto seatInvtDto = createSeatInvtDto();
		seatInvtDao.insertForTest(seatInvtDto);

		Map map = new HashMap();
		map.put("perfRoundId", seatInvtDto.getPerfRoundId());
		map.put("seatId", seatInvtDto.getSeatId());

		SeatInvtDto dtoBefore = seatInvtDao.selectForTest(map);
		System.out.println(dtoBefore);

		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setSeatInvtId(dtoBefore.getId());
		int ticketCnt = 4;
		bookingRequest.setTicketCnt(ticketCnt);

		assertTrue(seatInvtDao.updateSeatInvtDecreaseSeatStock(bookingRequest) == 1);

		SeatInvtDto dtoAfter = seatInvtDao.selectForTest(map);

		assertEquals(ticketCnt, dtoBefore.getSeatStock() - dtoAfter.getSeatStock());
	}

	@Test
	public void updateSeatInvtIncreaseSeatStock() {
		seatInvtDao.deleteAllForTest();

		SeatInvtDto seatInvtDto = createSeatInvtDto();
		seatInvtDao.insertForTest(seatInvtDto);

		Map map = new HashMap();
		map.put("perfRoundId", seatInvtDto.getPerfRoundId());
		map.put("seatId", seatInvtDto.getSeatId());

		SeatInvtDto dtoBefore = seatInvtDao.selectForTest(map);
		System.out.println(dtoBefore);

		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setSeatInvtId(dtoBefore.getId());
		int ticketCnt = 4;
		bookingRequest.setTicketCnt(ticketCnt);

		assertTrue(seatInvtDao.updateSeatInvtIncreaseSeatStock(bookingRequest) == 1);

		SeatInvtDto dtoAfter = seatInvtDao.selectForTest(map);

		assertEquals(ticketCnt, dtoAfter.getSeatStock() - dtoBefore.getSeatStock());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	@Test
	public void deleteAllAndCount() {
		seatInvtDao.deleteAllForTest();
		assertEquals(0, seatInvtDao.countForTest());
	}

	@Test
	public void insertForTest() {
		seatInvtDao.deleteAllForTest();

		assertEquals(0, seatInvtDao.countForTest());
		seatInvtDao.insertForTest(createSeatInvtDto());
		assertEquals(1, seatInvtDao.countForTest());
	}

	@Test
	public void selectForTest() {
		seatInvtDao.deleteAllForTest();

		SeatInvtDto seatInvtDto = createSeatInvtDto();
		seatInvtDao.insertForTest(seatInvtDto);

		Map map = new HashMap();
		map.put("perfRoundId", seatInvtDto.getPerfRoundId());
		map.put("seatId", seatInvtDto.getSeatId());

		SeatInvtDto seatInvtDtoDB = seatInvtDao.selectForTest(map);
		System.out.println(seatInvtDtoDB);

		assertEquals(seatInvtDto.getPerfRoundId(), seatInvtDtoDB.getPerfRoundId());
		assertEquals(seatInvtDto.getSeatId(), seatInvtDtoDB.getSeatId());
	}


	/* Methods for Test */

	/**
	 * 테스트용 SeatInvtDto 생성
	 *
	 * @return SeatInvtDto
	 */
	private SeatInvtDto createSeatInvtDto() {
		return new SeatInvtDto(null, 1, 1, 1, 150, 100);
	}

}