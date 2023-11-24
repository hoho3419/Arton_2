package com.arton.app.controller;

import com.arton.app.domain.dto.UserDto;
import com.arton.app.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	RegisterService registerService;

	@GetMapping("/terms")
	public String termsForm() {
		return "terms";
	}

	@GetMapping("/join")
	public String registerForm() {
		return "register";
	}

	@PostMapping("/join")
	public String register(@Valid @ModelAttribute UserDto userDto, HttpServletRequest request) {
		if (registerService.join(userDto) != 1) {
			request.setAttribute("msg", "잘못된 입력입니다. 다시 입력해주세요.");
			request.setAttribute("uri", "/register/join");

			return "alert";
		}
		return "redirect:/login/login";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Ajax */

	/** id 중복 체크 - onchange 이벤트로 input 태그에 id 입력후 포커스 변경할 때 바로바로 체크 */
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam String userId) {
		int result;
		if (userId != null && idValChk(userId)) {
			result = registerService.idDuplicateCheck(userId);
		} else {
			result = 99;
		}
		return result;
	}

	/** id validation 체크 (정규식) */
	public boolean idValChk(String str) {
		return Pattern.matches("^[a-z0-9_-]{5,20}$", str);
	}
}

