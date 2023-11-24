package com.arton.app.controller;

import com.arton.app.domain.request.BookingRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/test")
	public String test(@ModelAttribute BookingRequest bookingRequest, HttpServletRequest request) {
		return "test";
	}

	@PostMapping("/test2")
	public String test2(HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		Map map = new HashMap<>();
		while (params.hasMoreElements()) {
			String key = (String) params.nextElement();
			String value = request.getParameter(key);
			String tmp = key.split(";")[1];
			map.put(tmp, value);
		}
		System.out.println(map);

		return "test";
	}
}
