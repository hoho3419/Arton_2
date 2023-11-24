package com.arton.app.dao;

import com.arton.app.domain.RankingCondition;
import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.ViewSeatInvtDto;
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
public class PerfDaoTest {

	/** 초기 환경(DB에서 table drop 후 초기 쿼리문 입력상태)에서 진행되어야 하는 테스트입니다. */

	/*  몇몇 테스트에서는 독립적인 테스트를 포기한 이유(PerfDao, BookingDao)
	 *
	 * 기능별로 가능한 독립적으로 테스트 시 deleteAll 이후 새로운 record 삽입하는 방식으로 테스트하고 싶었으나
	 * 테이블 id 값이 auto_increment 라서, id 값을 알아내기 위해서
	 * 불필요하게(오직 테스트를 굴리기 위해) 만들어야하는 쿼리 및 과정들이 많았다.
	 * (=> 테스트에서도 DB의 기존 테이블을 계속 이용하기에 deleteAll 하고
	 * 새로운 record 를 만들었다고 하더라고 auto_increment 설정이
	 * 되어 있으므로 id 값이 1이 아님)
	 *
	 * JPA 의 hibernate.ddl-auto 설정을 통해 기존 테이블을 'drop' 해주고 새로 생성해주는 것이
	 * 왜 있는 기능인지 새삼 알게되었다.
	 *
	 * !!!물론 실제 운영중인 테이블이라면 ddl-auto 설정이 함부로 바뀌거나하면 안되고 애초에 그렇게 될 수 없어야 할 것 같다.
	 *
	 * */

	@Autowired
	PerfDao perfDao;
	//	@Autowired
	//	PerfRoundDao perfRoundDao;
	//	@Autowired
	//	SeatDao seatDao;
	//	@Autowired
	//	SeatInvtDao seatInvtDao;
	//	@Autowired
	//	UserDao userDao;
	//	@Autowired
	//	WishlistDao wishlistDao;
	//	@Autowired
	//	BookingDao bookingDao;


	@Test
	public void selectPerfByPerfId() {
		Integer perfId = 1001;
		PerfDto perfDto = perfDao.selectPerfByPerfId(perfId);
		System.out.println(perfDto);

		assertEquals(perfId, perfDto.getId());
	}

	@Test
	public void selectSeatInvtsByPerfId() {
		List<ViewSeatInvtDto> list = perfDao.selectSeatInvtsByPerfId(1001);

		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void selectPerfsByCustomizedCondition() {
		Map map = new HashMap();
		map.put("where", "AND cate = '콘서트'");
		map.put("order", "ORDER BY id DESC");
		map.put("limit", "LIMIT 0, 3");
		List<PerfDto> list = perfDao.selectPerfsByCustomizedCondition(map);

		assertEquals("콘서트", list.get(1).getCate());
		assertTrue(list.get(0).getId() > list.get(2).getId());
		assertEquals(3, list.size());
	}

	@Test
	public void selectPerfsOrderByBookingCount() {
		RankingCondition rc = new RankingCondition(1, "20221010", "w");
		List<PerfDto> list = perfDao.selectPerfsOrderByBookingCount(rc);

		assertEquals("콘서트", list.get(0).getCate());
		assertTrue(list.get(0).getBookingCnt() > list.get(1).getBookingCnt());
	}


	@Test
	public void selectPerfsOrderByLikeCount() {
		List<PerfDto> list = perfDao.selectPerfsOrderByLikeCount(1);

		assertEquals("콘서트", list.get(0).getCate());
		assertTrue(list.get(0).getLikeCnt() > list.get(1).getLikeCnt());
	}

	@Test
	public void selectWishlistsByUserIdx() {
		Integer userIdx = 1;
		List<PerfDto> list = perfDao.selectWishlistsByUserIdx(userIdx);

		assertNotNull(list);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */
	//	@Test
	//	public void deleteAllAndCount() {
	//		perfDao.deleteAllForTest();
	//		perfRoundDao.deleteAllForTest();
	//		seatDao.deleteAllForTest();
	//		seatInvtDao.deleteAllForTest();
	//		userDao.deleteAllForTest();
	//		wishlistDao.deleteAllForTest();
	//		bookingDao.deleteAllForTest();
	//
	//
	//		assertEquals(0, perfDao.countForTest());
	//		assertEquals(0, perfRoundDao.countForTest());
	//		assertEquals(0, seatDao.countForTest());
	//		assertEquals(0, seatInvtDao.countForTest());
	//		assertEquals(0, userDao.countForTest());
	//		assertEquals(0, wishlistDao.countForTest());
	//		assertEquals(0, bookingDao.countForTest());
	//	}
	//
	//	@Test
	//	public void insertForTest() {
	//		perfDao.deleteAllForTest();
	//
	//		assertEquals(0, perfDao.countForTest());
	//		perfDao.insertForTest(createPerfDto(1));
	//		assertEquals(1, perfDao.countForTest());
	//
	//	}
	//
	// /* Methods for Test */
	// /** PerfDto 생성 */
	//	private PerfDto createPerfDto(int num) {
	//		return new PerfDto("카테", "공연" + num, "포스터경로", "포스터경로", "등급", "러닝타임", "출연", "제작", "문의", "공연장", "공연장주소", 0, 1);
	//	}
	//
	//	private PerfRoundDto createPerfRoundDto() {
	//
	//	}
	//
	//	/** PerfDao 테스트용 초기 환경 세팅 */
	//	private void InitSetup() {
	//		// user 세팅
	//		userDao.insert(new UserDto(null,
	//				"test",
	//				"asdf",
	//				"name",
	//				"email@email.com",
	//				"19990101",
	//				"D",
	//				"01012345678",
	//				null,
	//				false,
	//				null,
	//				null));
	//
	//		// perf 세팅 (2개 공연)
	//		perfDao.insertForTest(createPerfDto(1));
	//		perfDao.insertForTest(createPerfDto(2));
	//
	//		// perfRound 세팅
	//	}

}