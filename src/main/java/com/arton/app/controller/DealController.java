package com.arton.app.controller;

import com.arton.app.domain.request.BookingRequest;
import com.arton.app.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/deal")
public class DealController {

	@Autowired
	DealService dealService;

	@GetMapping("/deal")
	public String dealForm(@RequestParam Integer perfCode, HttpSession session, Model m) {

		/* 공연 상세 정보 */
		m.addAttribute("perfDto", dealService.readPerf(perfCode));

		/* 예매 섹션 - 회차별 좌석 및 잔여석 정보 */
		m.addAttribute("seats", dealService.readSeatInfo(perfCode));

		/* 사용자의 해당 공연 찜 여부 확인 */
		Integer userIdx = (Integer) session.getAttribute("userIdx");
		if (userIdx != null) {
			m.addAttribute("isLiked", dealService.isLiked(userIdx, perfCode));
			System.out.println("################################");
			System.out.println(m);
		}

		return "deal";
	}


	/* Ajax - 예매 */
	@ResponseBody
	@PostMapping("/book")
	public Map book(@RequestBody BookingRequest bookingRequest,
					HttpSession session,
					HttpServletResponse response,
					Model m) throws IOException {

		Integer perfCode = bookingRequest.getPerfCode();
		Integer userIdx = (Integer) session.getAttribute("userIdx");
		bookingRequest.setUserIdx(userIdx);

		/* 만약의 경우 로그인 없이 넘어왔을 경우 로그인 페이지로*/
		if (userIdx == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.'); location.href='/app/login/login'</script>");
			out.flush();

			return null;
		}


		/* 좌석 차감 */
		int isBooked = dealService.minusSeatStock(bookingRequest);
		int cnt = 99;

		/* 좌석 차감 성공 시 예매데이터 삽입*/
		if (isBooked == 1) {
			cnt = dealService.makeBooking(bookingRequest); // cnt = 성공 시 1, 실패 시 0
		} else if (isBooked == 0) {
			cnt = 0;
		}

		Map map = new HashMap();
		map.put("seatStocks", dealService.updateSeatStock(perfCode)); // 잔여석 갱신을 위한 List<ViewSeatInvt> 받아오기
		map.put("result", cnt);

		return map;
	}

	/* Ajax - 찜하기 */
	@ResponseBody
	@PostMapping("/like")
	public Map<String, Integer> like(@RequestBody Map<String, String> map,
									 HttpSession session,
									 HttpServletResponse response) throws Exception {

		Integer userIdx = (Integer) session.getAttribute("userIdx");
		/* 만약의 경우 로그인 없이 넘어왔을 경우 로그인 페이지로*/
		if (userIdx == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.'); location.href='/app/login/login'</script>");
			out.flush();
			return new HashMap<>();
		}

		int perfCode = 0;
		try {
			perfCode = Integer.parseInt(map.get("perfCode"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		Integer liked = dealService.isLiked(userIdx, perfCode);
		if (liked == 1) {
			dealService.offLike(userIdx, perfCode);
		} else {
			dealService.onLike(userIdx, perfCode);
		}
		Integer isLiked = dealService.isLiked(userIdx, perfCode);
		Map<String, Integer> resultMap = new HashMap<>();

		resultMap.put("isLiked", isLiked);
		resultMap.put("likeCnt", dealService.readPerf(perfCode).getLikeCnt());

		return resultMap;
	}

	//
	//	@PostMapping("/deal")
	//	public String review(@RequestBody ReviewDto reviewDto, HttpServletRequest request, Model m) {
	//		Integer perfId = Integer.valueOf(request.getParameter("perfCode"));
	//
	//		HttpSession session = request.getSession();
	//		Integer userIdx = (Integer) session.getAttribute("userIdx");
	//
	//		// 리뷰 전체 가져오기
	//		List<ReviewDto> review = dealService.readList(perfId);
	//		m.addAttribute("review", review);
	//		System.out.println("review : " + review);
	//
	//		// 리뷰 작성
	//		reviewDto.setUserIdx(userIdx);
	//		reviewDto.setPerfId(perfId);
	//		dealService.write(reviewDto);
	//
	//		// 리뷰 수정
	//		dealService.modify(reviewDto);
	//
	//		// 리뷰 삭제
	//		String reviewId = request.getParameter("reviewId");
	//		Map map = new HashMap();
	//		map.put("reviewId", reviewId);
	//		map.put("userIdx", userIdx);
	//		dealService.remove(map);
	//
	//		return "deal";
	//	}


}
