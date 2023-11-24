package com.arton.app.dao;

import com.arton.app.domain.dto.BookingDto;
import com.arton.app.domain.request.BookingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BookingDaoTest {

	/** 초기 환경(DB에서 table drop 후 초기 쿼리문 입력상태)에서 진행되어야 하는 테스트입니다. */

	/*  몇몇 테스트에서는 독립적인 테스트를 포기한 이유(PerfDao, BookingDao)
	 *
	 * 기능별로 가능한 독립적으로 테스트 시 deleteAll 이후 새로운 record 삽입하는 방식으로 테스트하고 싶었으나
	 * 테이블 id 값이 auto_increment 라서, id 값을 알아내기 위해서
	 * 불필요하게(오직 테스트를 굴리기 위해) 만들어야하는 쿼리 및 과정들이 많았다.
	 *
	 * (=> 테스트에서도 DB의 기존 테이블을 계속 이용하기에 deleteAll 하고 새로운 record 를
	 * 		만들었다고 하더라고 auto_increment 설정이 되어 있으므로 id 값이 1이 아님)
	 *
	 * JPA 의 hibernate.ddl-auto 설정을 통해 기존 테이블을 'drop' 해주고
	 * 새로 생성해주는 것이 왜 필요한지를 새삼 알게되었다.
	 *
	 * !!!물론 실제 운영중인 테이블이라면 ddl-auto 설정이 함부로 바뀌거나하면 안되고 애초에 그렇게 될 수 없어야 할 것 같다.
	 *
	 * */

	@Autowired
	BookingDao bookingDao;

	@Test
	public void insertBooking() {
		int cnt = bookingDao.countForTest();

		BookingRequest br = new BookingRequest(1, 1, 4, "카드 결제", 1001);
		bookingDao.insertBooking(br);

		assertEquals(cnt + 1, bookingDao.countForTest());
	}

	@Test
	public void updateBookingStatus() {
		int bookingId = 1;
		assertTrue(bookingDao.updateBookingStatus(bookingId) == 1);

		BookingDto bookingDto = bookingDao.selectBookingById(1);
		assertTrue(bookingDto.getBookingStatus() == 0);
	}

	@Test
	public void selectBookingById() {
		BookingDto bookingDto = bookingDao.selectBookingById(1);
		assertNotNull(bookingDto);
	}

	@Test
	public void selectBookingsByUserIdx() {

		List<BookingDto> list = bookingDao.selectBookingsByUserIdx(1);
		assertNotNull(list);
		System.out.println(list.get(0).getPerfDto());
		System.out.println(list.get(0).getPerfRoundDto());
		System.out.println(list.get(0).getSeatDto());

	}

	@Test
	public void countBookingStatusByUserIdxAndPerfId() {
		Map map = new HashMap();
		map.put("userIdx", 1);
		map.put("perfId", 1001);
		int cnt = bookingDao.countBookingStatusByUserIdxAndPerfId(map);
		assertTrue(cnt > 1);

	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	@Test
	public void countForTest() {
		int cnt = bookingDao.countForTest();
		System.out.println(cnt);
	}
}