package com.arton.app.controller;

import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.UserDto;
import com.arton.app.domain.request.UserLoginRequest;
import com.arton.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();

		request.setAttribute("msg", "로그아웃 되었습니다.");
		request.setAttribute("uri", "/");

		return "alert";
	}

	//
	@GetMapping("/login")
	public String loginForm(HttpServletRequest request, Model m) {

		/* 예외 상황 - 만약에 로그인 했음에도 로그인 버튼이 보이는 에러상황에 대비함 */
		if (request.getSession().getAttribute("userId") != null) {
			request.setAttribute("msg", "이미 로그인한 상태입니다.");
			request.setAttribute("uri", "/");
			return "alert";
		}

		/* 로그인 화면 랜덤 포스터 */
		PerfDto perfDto = loginService.getRandomPerf();
		m.addAttribute("perfDto", perfDto);

		/* 로그인 후 로그인 직전 페이지로 이동할 수 있도록 세션에 prevPage 세팅 */
		String uri = request.getHeader("Referer");
		if (uri != null && !uri.contains("/login") && !uri.contains("/register")) {
			request.getSession().setAttribute("prevPage", uri);
		}

		return "login";
	}

	/* @RequestParam 과 @ModelAttribute 는 생략이 가능하다.(명시적으로 붙여주는 것도 좋다)
	 * WebDataBinder 를 통해 파라미터가 매핑된다.
	 * 하지만 required, defaultValue 및 value 등 설정이 필요한 경우 작성하는 것을 잊지 말자.
	 *
	 * 참고 - https://www.inflearn.com/questions/22857
	 * ref - https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-ann-modelattrib-method-args */
	@PostMapping("/login")
	public String login(@ModelAttribute UserLoginRequest userLoginRequest,
						HttpServletRequest request,
						HttpServletResponse response) throws Exception {

		/* 쿠키 생성 */
		Cookie cookie = new Cookie("userId", userLoginRequest.getUserId());
		cookie.setMaxAge(60 * 60 * 24 * 365);
		if (!userLoginRequest.isRememberId()) {
			cookie.setMaxAge(0); // 쿠키 삭제
		}
		response.addCookie(cookie);

		/* 로그인 시도 및 리턴값 저장 */
		UserDto userDto = loginService.login(userLoginRequest);

		/* 로그인 확인 */
		if (userDto == null) {
			request.setAttribute("msg", "일치하는 ID/PW가 없습니다.");
			request.setAttribute("uri", "/login/login");
			return "alert";

		} else { /*로그인 성공 후*/
			/* 최종 로그인 시간 갱신 */
			loginService.updateLoginTime(userDto.getIdx());

			/* 세션에 userId, userIdx 저장 */
			HttpSession session = request.getSession();
			session.setAttribute("userId", userDto.getUserId());
			session.setAttribute("userIdx", userDto.getIdx());

			/* 마지막으로 넘어온 페이지로 이동 - GetMapping("/login") 참고*/
			String uri = (String) session.getAttribute("prevPage");

			if (uri != null) {
				session.removeAttribute("prevPage"); // 세션에서 prevPage 삭제
			} else {
				uri = "/"; // 이전 페이지 정보가 없을 경우 index 로 이동
			}

			return "redirect:" + uri;
		}
	}
}

