package com.arton.app.service;

import com.arton.app.dao.*;
import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.ViewSeatInvtDto;
import com.arton.app.domain.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DealService {

	@Autowired
	PerfDao perfDao;
	@Autowired
	PerfDetailDao perfDetailDao;
	@Autowired
	WishlistDao wishlistDao;
	@Autowired
	SeatInvtDao seatInvtDao;
	@Autowired
	BookingDao bookingDao;

	/* 처음엔 Predicate 를 리턴하는 distinctByKey 를 사용해서 적용해보려 했으나 groupingBy 를 사용하는 것으로 변경함.
	 *
	 * **참고 -  https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
	 * */
	//	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
	//		Map<Object, Boolean> map = new HashMap<>();
	//		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	//	}

	/**
	 * <pre>
	 * groupingBy 할 때 바로 정렬된 리스트로 바꿔줌
	 *
	 * 정렬이 가능한 TreeSet 을 한번 거쳐서 ArrayList 로 반환.
	 *
	 * TreeSet 특징
	 * - 중복된 값을 허용하지 않는다
	 * - 데이터 정렬이 가능하다
	 * - null 값 저장 가능하다
	 * - 이진탐색트리(Red-Black Tree)를 사용하여 구현
	 *
	 * **참고 - <a href="https://stackoverflow.com/questions/35872236/sorting-lists-after-groupingby">sorting Lists after groupingBy - stackoverflow</a>
	 * </pre>
	 */
	static <T> Collector<T, ?, List<T>> toSortedList(Comparator<? super T> c) {
		return Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(c)), ArrayList::new);
	}


	/** 공연 상세 정보 */
	public PerfDto readPerf(Integer perfCode) {
		PerfDto perfDto = perfDao.selectPerfByPerfId(perfCode);

		StringBuffer sb = new StringBuffer(perfDto.getPerfStart());
		sb.insert(6, ".").insert(4, ".");
		perfDto.setPerfStart(sb.toString());

		sb = new StringBuffer(perfDto.getPerfEnd());
		sb.insert(6, ".").insert(4, ".");
		perfDto.setPerfEnd(sb.toString());

		return perfDto;
	}

	/** 예매 섹션 - 회차별 좌석 및 잔여석 정보 */
	public Map<Integer, Map<Integer, List<ViewSeatInvtDto>>> readSeatInfo(Integer perfCode) {
		List<ViewSeatInvtDto> seatInvtList = perfDao.selectSeatInvtsByPerfId(perfCode);

		seatInvtList = ServiceMethods.stringToDateFormat(seatInvtList);
		/* 처음엔 Predicate 를 리턴하는 distinctByKey 를 사용해서 적용해보려 했으나 groupingBy 를 사용하는 것으로 변경함.*/
		//		Map<String, List<ViewSeatInvtDto>> map = new HashMap<>();
		//		map.put("dates",
		//				SeatInvtList.stream().filter(distinctByKey(ViewSeatInvtDto::getShowDate)).collect(Collectors.toList()));
		//		map.put("times",
		//				SeatInvtList.stream().filter(distinctByKey(ViewSeatInvtDto::getShowTime)).collect(Collectors.toList()));
		//		map.put("seats", SeatInvtList);


		Map<Integer, Map<Integer, List<ViewSeatInvtDto>>> map = seatInvtList.stream()
				.collect(groupingBy(ViewSeatInvtDto::getShowDateIdx,
						TreeMap::new,
						groupingBy(ViewSeatInvtDto::getShowTimeIdx, toSortedList(ViewSeatInvtDto::compareTo))));


		return map;
	}

	/** 사용자의 해당 공연 찜 여부 확인 */
	public Integer isLiked(Integer userIdx, Integer perfCode) {
		Map map = new HashMap();
		map.put("userIdx", userIdx);
		map.put("perfId", perfCode);
		return wishlistDao.selectWishlistByUserIdxAndPerfId(map) != null ? 1 : 0;
	}

	/** 찜하기 */
	public void onLike(Integer userIdx, Integer perfId) {
		Map map = new HashMap();
		map.put("userIdx", userIdx);
		map.put("perfId", perfId);
		wishlistDao.insertWishlist(map);
	}

	/** 찜하기 취소 */
	public void offLike(Integer userIdx, Integer perfId) {
		Map map = new HashMap();
		map.put("userIdx", userIdx);
		map.put("perfId", perfId);
		wishlistDao.deleteWishlist(map);
	}

	/* 예매하기 */

	/** 예매 전 좌석 차감 시도 */
	public int minusSeatStock(BookingRequest bookingRequest) {
		return seatInvtDao.updateSeatInvtDecreaseSeatStock(bookingRequest);
	}

	/** 예매 정보 생성하기 */
	public int makeBooking(BookingRequest bookingRequest) {
		return bookingDao.insertBooking(bookingRequest);
	}

	/** 예매 섹션 - Ajax 이후 잔여석 갱신 */
	public List<ViewSeatInvtDto> updateSeatStock(Integer perfCode) {
		return perfDao.selectSeatInvtsByPerfId(perfCode);
	}
}
